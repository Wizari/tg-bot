package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.AbstractKeyboard;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardOne;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardTwo;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseButtonsTwoImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Hello!");

        AbstractKeyboard keyboard = new KeyboardTwo();
        responseMessage = keyboard.addKeyboard(update, responseMessage);

        return new ResponseEntity(responseMessage, true);
    }


    @Override
    public String getTeg() {
        return "2";
    }
}
