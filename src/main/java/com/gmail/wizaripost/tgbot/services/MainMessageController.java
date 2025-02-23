package com.gmail.wizaripost.tgbot.services;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.responses.AbstractResponse;
import com.gmail.wizaripost.tgbot.services.responses.Test;
import com.gmail.wizaripost.tgbot.services.responses.Test1;
import com.vdurmont.emoji.EmojiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
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

    public ResponseEntity getReply(Update update, String text) {
//        System.out.println("id: " + update.getMessage().getFrom().getId());
//        System.out.println("Callback id: " + update.getCallbackQuery().getFrom().getId());

        synchronized (telegramSynchronizedService.getMessageLock(update)) {
//            System.out.println(update.getMessage().toString());
//            System.out.println(update.getMessage().getText());

            for (AbstractResponse res : this.response) {
//                if (res.getTeg().equals(update.getMessage().getText())) {
                if (text.startsWith(res.getTeg())) {
                    return res.generateSendMessage(update);
                }
            }
//emoji
            if (EmojiManager.containsEmoji(text)) {
                for (AbstractResponse res : this.response) {
                    if (res.getTeg().equals("emoji")) {
                        return res.generateSendMessage(update);
                    }
                }
            }

//todo мб починить error
//Error: Incorrect command
            for (AbstractResponse res : this.response) {
                if (res.getTeg().equals("error")) {
                    return res.generateSendMessage(update);
                }
            }
//Error: Incorrect command
            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(String.valueOf(text));
            responseMessage.setText("Incorrect command");
            ResponseEntity response = new ResponseEntity();
            response.setResponse(responseMessage);
            return response;
        }
    }
}
