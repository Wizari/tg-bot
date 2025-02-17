package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
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

    protected ReplyKeyboardMarkup assembleReplyKeyboardMarkup(List<List<String>> buttons) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
//        markup.setOneTimeKeyboard(true);
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
