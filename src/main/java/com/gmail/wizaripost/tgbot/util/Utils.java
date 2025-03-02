package com.gmail.wizaripost.tgbot.util;

import org.telegram.telegrambots.meta.api.objects.Update;

public class Utils {
    public static String getChatId(Update update) {
        if (update.hasMessage()) {
            return String.valueOf(update.getMessage().getChatId());
        }
        if (update.hasCallbackQuery()) {
            return String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        }
        return null;
    }

    public static Long getUserId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getFrom().getId();
        }
        return null;
    }
}
