package com.gmail.wizaripost.tgbot.services.buttons;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;
import java.util.stream.Collectors;

@Component
public abstract class AbstractButtons {
    public abstract ReplyKeyboardMarkup generate();

    public abstract String getTeg();

    protected ReplyKeyboardMarkup assembleButtons(List<List<String>> buttons) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setKeyboard(
                buttons.stream()
                        .map(rowButtons -> {
                            KeyboardRow row = new KeyboardRow();
                            rowButtons.forEach(row::add); // Добавляем кнопки в строку
                            return row;
                        })
                        .collect(Collectors.toList()) // Собираем все строки в клавиатуру
        );
        return markup;
    }
}
