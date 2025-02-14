package com.gmail.wizaripost.tgbot.services.buttons;

import com.gmail.wizaripost.tgbot.model.ChatState;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

public class ButtonsRemoveImpl extends AbstractButtons {
    @Override
    public ReplyKeyboard generate() {
        ReplyKeyboardRemove keyboardRemove = new ReplyKeyboardRemove();
        keyboardRemove.setRemoveKeyboard(true); // Указываем, что клавиатуру нужно удалить
        keyboardRemove.setSelective(false); // Удаляем клавиатуру для всех пользователей в чате
        return keyboardRemove;
    }

    @Override
    public ChatState getTeg() {
        return ChatState.REMOVE;
    }
}
