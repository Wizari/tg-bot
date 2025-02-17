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

    //    public SendMessage getReply(Update update) {
    public ResponseEntity getReply(Update update) {
        synchronized (telegramSynchronizedService.getMessageLock(update.getMessage().getFrom().getId())) {
//            System.out.println(update.getMessage().toString());
//            System.out.println(update.getMessage().getText());

//            String string = update.getMessage().getText();
            HashMap<Object, Object> map = new HashMap<>();
            Long chatId;
            Integer userId;
            Update update1 = null;
            Integer i;
            if (map.isEmpty()) {
                System.out.println("map.isEmpty()");
                map.putIfAbsent(1, 1);
                chatId = update.getMessage().getChatId();
                userId = update.getMessage().getMessageId();
                update1 = update;
                Test1 test1 = new Test1();
                test1.generateSendMessage(update);
                for (AbstractResponse res : this.response) {
                    if (res.getTeg().equals("/test1")) {
                        return res.generateSendMessage(update);
                    }
                }
            }

                for (AbstractResponse res : this.response) {
                    if (res.getTeg().equals("/test2")) {
                        map.clear();
                        System.out.println("map.clear()");

                        return res.generateSendMessage(update1);
                    }
                }








//            for (AbstractResponse res : this.response) {
//                if (res.getTeg().equals(update.getMessage().getText())) {
//                    return res.generateSendMessage(update);
//                }
//            }
////emoji
//            if (EmojiManager.containsEmoji(update.getMessage().getText())) {
//                for (AbstractResponse res : this.response) {
//                    if (res.getTeg().equals("emoji")) {
//                        return res.generateSendMessage(update);
//                    }
//                }
//            }
//
//
////Error: Incorrect command
//            for (AbstractResponse res : this.response) {
//                if (res.getTeg().equals("error")) {
//                    return res.generateSendMessage(update);
//                }
//            }
//Error: Incorrect command
            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            responseMessage.setText("Incorrect command");
            ResponseEntity response = new ResponseEntity();
            response.setResponse(responseMessage);
            return response;
        }
    }
}
