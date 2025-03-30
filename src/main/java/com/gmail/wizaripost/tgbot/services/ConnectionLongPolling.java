package com.gmail.wizaripost.tgbot.services;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.util.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.gmail.wizaripost.tgbot.util.Utils.getChatId;


@Service
@PropertySource("classpath:secrets.properties")
public class ConnectionLongPolling extends TelegramLongPollingBot {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    private final MainMessageController mainMessageController;
    @Value("${telegram.botName}")
    private String botUsername;

    @Autowired
    public ConnectionLongPolling(@Value("${telegram.token}") String botToken,
                                 MainMessageController mainMessageController
    ) {

        super(botToken);
        this.mainMessageController = mainMessageController;
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("Получено обновление: " + update);
        Mono.fromRunnable(() -> handleUpdate(update))
                .subscribeOn(Schedulers.fromExecutor(executor))
                .subscribe();
    }


    private void handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasLocation()) {
            System.out.println("getLatitude: " + update.getMessage().getLocation().getLatitude());

            this.sendResponseToLocation(update);
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            System.out.println("update.getMessage().getFrom().getId(): "
                    + update.getMessage().getFrom().getId());

            this.sendResponseToMessage(update);
        }
        if (update.hasCallbackQuery()) {
            System.out.println("callback: " + update.getCallbackQuery().getData());

            this.sendResponseToCallbackQuery(update);
        }
    }

    private void sendResponseToMessage(Update update) {
        try {
            ResponseEntity responseEntity = mainMessageController.getReply(update,
                    States.INSTANCE.getStatePrefix(getChatId(update))
                            + update.getMessage().getText());
            if (responseEntity.isDeleteMessage()) {
                this.deleteMessage(update.getMessage().getChatId(),
                        update.getMessage().getMessageId());
            }

            if (responseEntity.getResponse() instanceof BotApiMethod) {
                myExecute((BotApiMethod) responseEntity.getResponse());
            }
            if (responseEntity.getResponse() instanceof SendPhoto) {
                myExecute((SendPhoto) responseEntity.getResponse());
            }
            if (responseEntity.getResponse() instanceof SendMediaGroup) {
                myExecute((SendMediaGroup) responseEntity.getResponse());
            }
            if (responseEntity.getResponse() instanceof EditMessageReplyMarkup) {
                myExecute((EditMessageReplyMarkup) responseEntity.getResponse());
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendResponseToCallbackQuery(Update update) {
        ResponseEntity responseEntity = mainMessageController.getReply(update,
                update.getCallbackQuery().getData());

        if (responseEntity.getResponse() instanceof BotApiMethod) {
            myExecute((BotApiMethod) responseEntity.getResponse());
        }
        if (responseEntity.getResponse() instanceof SendPhoto) {
            myExecute((SendPhoto) responseEntity.getResponse());
        }
        if (responseEntity.getResponse() instanceof SendMediaGroup) {
            myExecute((SendMediaGroup) responseEntity.getResponse());
        }
    }

    private void sendResponseToLocation(Update update) {
        try {
            ResponseEntity responseEntity = mainMessageController.getReply(update,
                    "/locationMessage");
            if (responseEntity.isDeleteMessage()) {
                this.deleteMessage(update.getMessage().getChatId(),
                        update.getMessage().getMessageId());
            }
            if (responseEntity.getResponse() instanceof BotApiMethod) {
                myExecute((BotApiMethod) responseEntity.getResponse());
            }

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    public void deleteMessage(Long chatId, Integer messageId) throws TelegramApiException {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId.toString());
        deleteMessage.setMessageId(messageId);
        myExecute(deleteMessage);
    }

    private void myExecute(BotApiMethod res) {
        Mono.fromCallable(() -> execute(res))
                .subscribeOn(Schedulers.boundedElastic()) // Блокирующий вызов в отдельном потоке
                .subscribe(
                        result -> {},
                        error -> System.err.println("Error: " + error)
                );
    }

    private void myExecute(SendPhoto res) {
        System.out.println("myExecute");
        Mono.fromCallable(() -> execute(res))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(
                        result -> {},
                        error -> System.err.println("Error: " + error)
                );
    }

    private void myExecute(SendMediaGroup res) {
        System.out.println("myExecute");
        Mono.fromCallable(() -> execute(res))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(
                        result -> {},
                        error -> System.err.println("Error: " + error)
                );
    }

    private void myExecute(EditMessageReplyMarkup res) {
        System.out.println("myExecute");
        Mono.fromCallable(() -> execute(res))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(
                        result -> {},
                        error -> System.err.println("Error: " + error)
                );
    }

}
