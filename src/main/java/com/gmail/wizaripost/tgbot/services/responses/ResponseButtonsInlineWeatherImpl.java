package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.AbstractKeyboard;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardInline;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardInlineWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseButtonsInlineWeatherImpl extends AbstractResponse {

    private final UserService userService;

    @Autowired
    public ResponseButtonsInlineWeatherImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Weather!");

        AbstractKeyboard keyboard = new KeyboardInlineWeather(userService);
        responseMessage =(SendMessage) keyboard.addKeyboard(update, responseMessage);

        return new ResponseEntity(responseMessage);
    }


    @Override
    public String getTeg() {
        return "/inlineWeather";
    }

}
