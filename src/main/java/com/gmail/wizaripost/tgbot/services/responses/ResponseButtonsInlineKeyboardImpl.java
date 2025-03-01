package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardInline;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseButtonsInlineKeyboardImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
//        responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Hello!");

//        KeyboardInline keyboard = new KeyboardInline();
        KeyboardInline keyboard = new KeyboardInline();
        responseMessage =(SendMessage) keyboard.addKeyboard(update, responseMessage);

        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        return response;
    }


    @Override
    public String getTeg() {
        return "/testInline";
    }

}
