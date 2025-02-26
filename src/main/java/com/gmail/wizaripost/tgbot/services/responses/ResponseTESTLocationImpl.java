package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.entity.Location;
import com.gmail.wizaripost.tgbot.entity.User;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseTESTLocationImpl extends AbstractResponse {

    private final UserService userService;

    @Autowired
    public ResponseTESTLocationImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity generateSendMessage(Update update) {
        String message = update.getMessage().getText();
        Long telegramUserId = update.getMessage().getFrom().getId();
        String responseText;


        if (!userService.haveUserByTelegramId(telegramUserId)) {
            // Если пользователя нет, создаем нового
            User user = new User(null, "anonymous", telegramUserId, null);
            userService.saveUser(user);
            responseText = "Hello";
        } else {
//            User user = userService.getUserByTelegramId(telegramUserId);
            User user = userService.getUserWithLocations(telegramUserId);
            if (user != null) {
                Location location1 = new Location();
                location1.setLatitude(55.7558);
                location1.setLongitude(37.6176);
                location1.setName("Moscow");

                Location location2 = new Location();
                location2.setLatitude(59.9343);
                location2.setLongitude(30.3351);
                location2.setName("Saint Petersburg");

                user.addLocation(location1);
                user.addLocation(location2);

                userService.saveUser(user);
                responseText = "123";
            } else {
                responseText = "Registration aborted";
            }

        }


        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.setText(responseText);

        KeyboardOne keyboardOne = new KeyboardOne();
        responseMessage = keyboardOne.addKeyboard(update, responseMessage);

        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        return response;
    }

    @Override
    public String getTeg() {
        return "/add";
    }

}
