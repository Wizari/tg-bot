package com.gmail.wizaripost.tgbot.services.buttons;

import com.gmail.wizaripost.tgbot.model.ChatState;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class ButtonsOneImpl extends AbstractButtons {
    @Override
    public ReplyKeyboardMarkup generate() {
        return null;
    }

    @Override
    public ChatState getTeg() {
        return ChatState.ONE;
    }
}
