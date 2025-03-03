package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardOne;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseHelpImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Для добавления локации!");

        return new ResponseEntity(responseMessage);
    }

    @Override
    public String getTeg() {
        return "/help";
    }
}
