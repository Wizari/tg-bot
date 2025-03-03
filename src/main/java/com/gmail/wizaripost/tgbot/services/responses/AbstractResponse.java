package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.AppState;
import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.util.States;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class AbstractResponse {

    public abstract ResponseEntity generateSendMessage(Update update);

    public abstract String getTeg();

//    protected String getChatId(Update update) {
//        if (update.hasMessage()) {
//            return String.valueOf(update.getMessage().getChatId());
//        }
//        if (update.hasCallbackQuery()) {
//            return String.valueOf(update.getCallbackQuery().getMessage().getChatId());
//        }
//        return null;
//    }
    protected Long getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        return null;
    }

    protected Long getUserId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        return null;
    }

    public boolean postfixAllowed() {
        return false;
    }




}
