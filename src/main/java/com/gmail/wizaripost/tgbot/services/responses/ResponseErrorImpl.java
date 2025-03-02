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
        return new ResponseEntity(responseMessage);

    }

    public ResponseEntity generateSendMessage(Update update, String exception) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Error: [ " + exception + " ]");
        return new ResponseEntity(responseMessage);

    }

    @Override
    public String getTeg() {
        return "error";
    }
}
