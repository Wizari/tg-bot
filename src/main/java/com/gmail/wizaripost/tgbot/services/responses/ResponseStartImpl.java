package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardOne;
import com.gmail.wizaripost.tgbot.util.DelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseStartImpl extends AbstractResponse {

    @Autowired
    DelayService delayService;

    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Hello!");

//        delayService.delayAsync(7000).join(); // Блокирующий вызов

        KeyboardOne keyboardOne = new KeyboardOne();
        responseMessage = keyboardOne.addKeyboard(update, responseMessage);

        return new ResponseEntity(responseMessage);
    }

    @Override
    public String getTeg() {
        return "/start";
    }
}
