package com.gmail.wizaripost.tgbot.services;

import com.gmail.wizaripost.tgbot.services.responses.AbstractResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;


@Service
public class MainMessageController {


    @Autowired
    private Set<AbstractResponse> response;
    private final TelegramSynchronizedService telegramSynchronizedService;

    public MainMessageController(
            TelegramSynchronizedService telegramSynchronizedService
    ) {
        this.telegramSynchronizedService = telegramSynchronizedService;
    }

    //    public SendMessage getReply(Update update) {
    public BotApiMethod getReply(Update update) {
        synchronized (telegramSynchronizedService.getMessageLock(update.getMessage().getFrom().getId())) {
            for (AbstractResponse res : this.response) {
                if (res.getTeg().equals(update.getMessage().getText())) {
                    return res.generateSendMessage(update);
                }
            }


//Error: Incorrect command
            for (AbstractResponse res : this.response) {
                if (res.getTeg().equals("error")) {
                    return res.generateSendMessage(update);
                }
            }
//Error: Incorrect command
            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            responseMessage.setText("Incorrect command");
            return responseMessage;

        }
    }
}
