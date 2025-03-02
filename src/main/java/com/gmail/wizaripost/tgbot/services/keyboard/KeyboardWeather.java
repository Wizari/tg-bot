package com.gmail.wizaripost.tgbot.services.keyboard;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.entity.Location;
import com.gmail.wizaripost.tgbot.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.wizaripost.tgbot.util.Utils.getUserId;

@Service
public class KeyboardWeather extends AbstractKeyboard {

    private final UserService userService;

    @Autowired
    public KeyboardWeather(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SendMessage addKeyboard(Update update, SendMessage responseMessage) {
        List<List<String>> buttons = new ArrayList<>();
        User user = userService.getUserWithLocations(getUserId(update));

        for (Location location : user.getLocations()) {
            buttons.add(List.of(location.getName()));
        }

        responseMessage.enableMarkdown(true);
        responseMessage.setReplyMarkup(this.assembleReplyKeyboardMarkup(buttons));

        return responseMessage;
    }
}
