package com.gmail.wizaripost.tgbot.services;

import com.gmail.wizaripost.tgbot.model.AppState;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.responses.AbstractResponse;
import com.gmail.wizaripost.tgbot.util.States;
import com.vdurmont.emoji.EmojiManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;

import static com.gmail.wizaripost.tgbot.util.Utils.getChatId;


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
            States.INSTANCE.setState(getChatId(update), AppState.IDLE);
//            System.out.println(update.getMessage().toString());
//            System.out.println(update.getMessage().getText());

            for (AbstractResponse res : this.response) {
//                if (res.getTeg().equals(update.getMessage().getText())) {
                if (text.equals(res.getTeg())) {
//                    States.INSTANCE.setState(AppState.IDLE);
                    return res.generateSendMessage(update);
                }
            }

            for (AbstractResponse res : this.response) {
                if (text.startsWith(res.getTeg()) && res.postfixAllowed()) {
//                    States.INSTANCE.setState(AppState.IDLE);
                    return res.generateSendMessage(update);
                }
            }
//emoji
            if (EmojiManager.containsEmoji(text)) {
                for (AbstractResponse res : this.response) {
                    if (res.getTeg().equals("emoji")) {
//                        States.INSTANCE.setState(AppState.IDLE);
                        return res.generateSendMessage(update);
                    }
                }
            }

//Error: Incorrect command
            for (AbstractResponse res : this.response) {
                if (res.getTeg().equals("error")) {
//                    States.INSTANCE.setState(AppState.IDLE);
                    return res.generateSendMessage(update);
                }
            }
//Error: Incorrect command
            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(String.valueOf(text));
            responseMessage.setText("not found \"response\"r");
//            States.INSTANCE.setState(AppState.WEATHER);
            return new ResponseEntity(responseMessage);
        }
    }
}
