package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseErrorImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Error: Incorrect command!");
        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        return response;
    }

    public ResponseEntity generateSendMessage(Update update, String exception) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Error: [ " + exception + " ]");
        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        return response;
    }

    @Override
    public String getTeg() {
        return "error";
    }
}
