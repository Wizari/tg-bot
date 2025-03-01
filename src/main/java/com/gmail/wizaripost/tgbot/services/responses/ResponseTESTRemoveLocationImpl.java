package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.entity.Location;
import com.gmail.wizaripost.tgbot.entity.User;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseTESTRemoveLocationImpl extends AbstractResponse {

    private final UserService userService;

    @Autowired
    public ResponseTESTRemoveLocationImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity generateSendMessage(Update update) {
//        String message = update.getMessage().getText();
        Long telegramUserId = this.getUserId(update);
        String callbackData = update.getCallbackQuery().getData();
        int locationId = Integer.parseInt(callbackData.split("_")[1]);
        String responseText;


        if (!userService.haveUserByTelegramId(telegramUserId)) {
            User user = new User(null, "anonymous", telegramUserId, null);
            responseText = "Location not found";
            userService.saveUser(user);
        } else {
            User user = userService.getUserWithLocations(telegramUserId);
            if (user != null) {
                for (Location location : user.getLocations()) {
                    if (location.getId() == locationId) {
                        user.removeLocation(location);
                        userService.saveUser(user);
                        break;
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (Location location : user.getLocations()) {
                    stringBuilder.append("Location: " + location.getName() + "\n");
                }
                responseText = stringBuilder.toString();
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
        return "/removeLocation_";
    }


}
