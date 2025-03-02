package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.entity.Location;
import com.gmail.wizaripost.tgbot.entity.User;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.model.WeatherResponse;
import com.gmail.wizaripost.tgbot.services.WeatherService;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardOne;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class ResponseGetWeatherImpl extends AbstractResponse {

    @Autowired
    private WeatherService weatherService;
    private final UserService userService;

    @Autowired
    public ResponseGetWeatherImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity generateSendMessage(Update update) {
        String message;
        if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getData().substring(7);
        } else {
            message = update.getMessage().getText();
        }
        Long telegramUserId = getUserId(update);
        String responseText = "Location not found";

        if (!userService.haveUserByTelegramId(telegramUserId)) {
            User user = new User(null, "anonymous", telegramUserId, null);
            userService.saveUser(user);
        } else {
            User user = userService.getUserWithLocations(telegramUserId);
            if (user != null) {
                for (Location location : user.getLocations()) {
                    if (location.getName().equals(message)) {
                        WeatherResponse weatherResponse = weatherService.getWeather(location.getLatitude(),
                                location.getLongitude());
                        responseText = " "
                                + weatherResponse.getCurrent().getTemperature_2m()
                                + EmojiParser.parseToUnicode(":smile:");
                    }
                }
            }
        }


        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText(responseText);

        KeyboardOne keyboardOne = new KeyboardOne();
        responseMessage = keyboardOne.addKeyboard(update, responseMessage);

        return new ResponseEntity(responseMessage);
    }

    @Override
    public String getTeg() {
        return "WEATHER";
    }

    @Override
    public boolean postfixAllowed() {
        return true;
    }
}
