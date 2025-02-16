package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.WeatherResponse;
import com.gmail.wizaripost.tgbot.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseWeatherImpl extends AbstractResponse {

    @Autowired
    private WeatherService weatherService;

    @Override
    public SendMessage generateSendMessage(Update update) {
        WeatherResponse weatherResponse = weatherService.getWeather(60.01, 30.27);


        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        responseMessage.setText(" "
                + weatherResponse.getCurrent().getTemperature_2m());


        return responseMessage;
    }

    @Override
    public String getTeg() {
        return "/weather";
    }
}
