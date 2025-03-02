package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.db.services.UserService;
import com.gmail.wizaripost.tgbot.entity.User;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseRegistrationImpl extends AbstractResponse {

    private final UserService userService;

    @Autowired
    public ResponseRegistrationImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity generateSendMessage(Update update) {
        String message = update.getMessage().getText();
        Long telegramUserId = update.getMessage().getFrom().getId();
        String responseText;


        if (!userService.haveUserByTelegramId(telegramUserId)) {
            // Если пользователя нет, создаем нового
            User newUser = new User(null, message.substring(3), telegramUserId, null);
            userService.saveUser(newUser);
            responseText = String.format("Registration: Name = *%s*, tgId = %d", message.substring(3), telegramUserId);
        } else {
            // Если пользователь уже существует, обновляем его имя
            User user = userService.getUserByTelegramId(telegramUserId);
            if (user != null) {
                user.setName(message.substring(3));
                userService.saveUser(user);
                responseText = String.format("Rename: Name = *%s*, tgId = %d", message.substring(3), telegramUserId);
            } else {
                responseText = "Registration aborted";
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
        return "/r ";
    }

    @Override
    public boolean postfixAllowed() {
        return true;
    }
}
