package com.gmail.wizaripost.tgbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
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
//        System.out.println("Получено обновление1: " + update);
        if (update.hasMessage() && update.getMessage().hasText()) {
//            System.out.println("Получено сообщение2: " + update.getMessage().getText());
            try {
                execute(mainMessageController.getReply(update).getResponse());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            if (mainMessageController.getReply(update).isDeleteMessage()) {
                try {
                    deleteMessage(update.getMessage().getChatId(),
                            update.getMessage().getMessageId());
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

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
