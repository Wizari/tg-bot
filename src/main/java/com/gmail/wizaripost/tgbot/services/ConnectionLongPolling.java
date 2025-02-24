package com.gmail.wizaripost.tgbot.services;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaBotMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
@PropertySource("classpath:secrets.properties")
public class ConnectionLongPolling extends TelegramLongPollingBot {


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
        System.out.println("Получено обновление1: " + update);
        if (update.hasMessage() && update.getMessage().hasLocation()) {
            System.out.println("Получено сообщение2: " + update.getMessage().getLocation().toString());
            System.out.println("getLatitude: " + update.getMessage().getLocation().getLatitude());
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
//            System.out.println("Получено сообщение2: " + update.getMessage().getText());
//            System.out.println("Получено сообщение2: " + update.getMessage().getChatId());

            this.sendResponseToMessage(update);
        }
        if (update.hasCallbackQuery()) {
            System.out.println("callback: " + update.getCallbackQuery().getData());
//            System.out.println("callback: " + update);
//            System.out.println("callback: " + update.getCallbackQuery().getMessage().getChatId().toString());

            this.sendResponseToCallbackQuery(update);
        }
    }


    private void sendResponseToMessage(Update update) {
        try {
            ResponseEntity responseEntity = mainMessageController.getReply(update,
                    update.getMessage().getText());
            if (responseEntity.isDeleteMessage()) {
                this.deleteMessage(update.getMessage().getChatId(),
                        update.getMessage().getMessageId());
            }

            if (responseEntity.getResponse() instanceof BotApiMethod) {
                execute((BotApiMethod) responseEntity.getResponse());
            }
            if (responseEntity.getResponse() instanceof SendPhoto) {
                execute((SendPhoto) responseEntity.getResponse());
            }
            if (responseEntity.getResponse() instanceof SendMediaGroup) {
                execute((SendMediaGroup) responseEntity.getResponse());
            }
            if (responseEntity.getResponse() instanceof EditMessageReplyMarkup) {
                execute((EditMessageReplyMarkup) responseEntity.getResponse());
            }



//            execute((SendMessage) responseEntity.getResponse());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendResponseToCallbackQuery(Update update) {
        try {
            ResponseEntity responseEntity = mainMessageController.getReply(update,
                    update.getCallbackQuery().getData());
//            execute((SendMessage) responseEntity.getResponse());


            if (responseEntity.getResponse() instanceof BotApiMethod) {
                execute((BotApiMethod) responseEntity.getResponse());
            }
            if (responseEntity.getResponse() instanceof SendPhoto) {
                execute((SendPhoto) responseEntity.getResponse());
            }
            if (responseEntity.getResponse() instanceof SendMediaGroup) {
                execute((SendMediaGroup) responseEntity.getResponse());
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
        execute(deleteMessage);
    }
}
