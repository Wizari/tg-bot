package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseStartImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        responseMessage.setText("Hello!");
        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        return response;
    }

    @Override
    public String getTeg() {
        return "/start";
    }
}
