package com.gmail.wizaripost.tgbot.services.buttons;

import com.gmail.wizaripost.tgbot.model.ChatState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Component
public class ButtonsIDLEImpl extends AbstractButtons {
    @Override
    public ReplyKeyboardMarkup generate() {
        // Создаем клавиатуру
        List<List<String>> buttons = new ArrayList<>();
        buttons.add(List.of("/start", "когда ты обновлялась?", "когда ты обновлялась?"));
        buttons.add(List.of("/r User1", "Hello"));
        buttons.add(List.of("/r User1", "Привет"));
        buttons.add(List.of("<", ">"));
        return this.assembleButtons(buttons);
    }

    @Override
    public ChatState getTeg() {
        return ChatState.IDLE;
    }

}
