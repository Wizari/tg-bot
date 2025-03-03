package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.entity.Location;
import com.gmail.wizaripost.tgbot.entity.User;
import com.gmail.wizaripost.tgbot.model.AppState;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.model.WeatherResponse;
import com.gmail.wizaripost.tgbot.services.WeatherService;
import com.gmail.wizaripost.tgbot.services.keyboard.AbstractKeyboard;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardWeather;
import com.gmail.wizaripost.tgbot.util.States;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class ResponseAddWeatherLocationImpl extends AbstractResponse {


    @Autowired
    private WeatherService weatherService;
    private final UserService userService;

    @Autowired
    public ResponseAddWeatherLocationImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity generateSendMessage(Update update) {
        String message;
        if (update.hasCallbackQuery()) {
            message = update.getCallbackQuery().getData().substring(11);
        } else {
            message = update.getMessage().getText();
        }
        Long telegramUserId = getUserId(update);
        String responseText = "Location not saved";

        String[] parts = message.split(" ", 3);
        double latitude = Double.parseDouble(parts[0]);
        double longitude = Double.parseDouble(parts[1]);

        String locationName = parts[2];
        System.out.println(latitude);
        System.out.println(longitude);
        System.out.println(locationName);


        if (!userService.haveUserByTelegramId(telegramUserId)) {
            User user = new User(null, "anonymous", telegramUserId, null);
            user.addLocation(new Location(latitude, longitude, locationName));
            userService.saveUser(user);
            responseText = "Location saved";
        } else {
            User user = userService.getUserWithLocations(telegramUserId);
            if (user != null) {
                user.addLocation(new Location(latitude, longitude, locationName));
                userService.saveUser(user);
                responseText = "Location saved";
            }
        }

        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText(responseText);

        AbstractKeyboard keyboard = new KeyboardWeather(userService);
        responseMessage = (SendMessage) keyboard.addKeyboard(update, responseMessage);

        States.INSTANCE.setState(getChatId(update), AppState.WEATHER);

        return new ResponseEntity(responseMessage);
    }


    @Override
    public String getTeg() {
        return "ADD_WEATHER";
    }

    @Override
    public boolean postfixAllowed() {
        return true;
    }
}
