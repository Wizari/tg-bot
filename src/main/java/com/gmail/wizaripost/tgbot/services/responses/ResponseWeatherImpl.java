package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.model.AppState;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.WeatherService;
import com.gmail.wizaripost.tgbot.services.keyboard.AbstractKeyboard;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardWeather;
import com.gmail.wizaripost.tgbot.util.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class ResponseWeatherImpl extends AbstractResponse {

    @Autowired
    private final UserService userService;

    @Autowired
    public ResponseWeatherImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText("Hello!");

        AbstractKeyboard keyboard = new KeyboardWeather(userService);
        responseMessage = (SendMessage) keyboard.addKeyboard(update, responseMessage);

        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        States.INSTANCE.setState(AppState.WEATHER);
        return response;
    }


    @Override
    public String getTeg() {
        return "/weather";
    }
}
