package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.entity.Location;
import com.gmail.wizaripost.tgbot.entity.User;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.AbstractKeyboard;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardInlineWeatherDelete;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
public class ResponseDeleteWeatherLocationImpl extends AbstractResponse {

    private final UserService userService;

    @Autowired
    public ResponseDeleteWeatherLocationImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity generateSendMessage(Update update) {
        Long telegramUserId = this.getUserId(update);
        String callbackData = update.getCallbackQuery().getData();
        int locationId = Integer.parseInt(callbackData.split("DELETE_WEATHER")[1]);
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
                responseText = "Location removal successful";
            } else {
                responseText = "Delete aborted";
            }

        }

        // Обновление сообщения с клавиатурой
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(getChatId(update));
        editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editMessage.setText("Delete Location");
        AbstractKeyboard keyboard = new KeyboardInlineWeatherDelete(userService);
        SendMessage responseMessage = new SendMessage();
        responseMessage = keyboard.addKeyboard(update, responseMessage);

        editMessage.setReplyMarkup((InlineKeyboardMarkup) responseMessage.getReplyMarkup());

        return new ResponseEntity(editMessage);
    }

    @Override
    public String getTeg() {
        return "DELETE_WEATHER";
    }

    @Override
    public boolean postfixAllowed() {
        return true;
    }
}
