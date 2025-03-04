package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.model.AppState;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.AbstractKeyboard;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardInlineWeatherDelete;
import com.gmail.wizaripost.tgbot.util.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class ResponseButtonDeleteWeatherImpl extends AbstractResponse {

    private final UserService userService;

    @Autowired
    public ResponseButtonDeleteWeatherImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Delete Location");

        AbstractKeyboard keyboard = new KeyboardInlineWeatherDelete(userService);
        responseMessage = (SendMessage) keyboard.addKeyboard(update, responseMessage);


        States.INSTANCE.setState(getChatId(update), AppState.DELETE_WEATHER);

        return new ResponseEntity(responseMessage);
    }


    @Override
    public String getTeg() {
        return "/deleteLocation";
    }
}
