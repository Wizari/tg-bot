package com.gmail.wizaripost.tgbot.services.keyboard;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.entity.Location;
import com.gmail.wizaripost.tgbot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.gmail.wizaripost.tgbot.util.Utils.getUserId;

@Service
public class KeyboardInlineWeatherDelete extends AbstractKeyboard {


    private final UserService userService;

    @Autowired
    public KeyboardInlineWeatherDelete(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SendMessage addKeyboard(Update update, SendMessage responseMessage) {
        List<Map<String, String>> buttons = new ArrayList<>();
        User user = userService.getUserWithLocations(getUserId(update));

        for (Location location : user.getLocations()) {
            buttons.add(Map.of(location.getName(), "DELETE_WEATHER" + location.getId()));
        }

        responseMessage.enableMarkdown(true);
        responseMessage.setReplyMarkup(this.createInlineKeyboard(buttons));
        return responseMessage;
    }
}
