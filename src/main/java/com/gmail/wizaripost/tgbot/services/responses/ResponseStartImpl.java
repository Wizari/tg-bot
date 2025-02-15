package com.gmail.wizaripost.tgbot.services.responses;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseStartImpl extends AbstractResponse {
    @Override
    public SendMessage generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        responseMessage.setText("Hello!");


        return responseMessage;
    }

    @Override
    public String getTeg() {
        return "/start";
    }
}
