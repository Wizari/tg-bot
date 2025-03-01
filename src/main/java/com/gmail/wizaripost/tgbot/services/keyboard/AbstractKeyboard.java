package com.gmail.wizaripost.tgbot.services.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public abstract class AbstractKeyboard {

    public abstract SendMessage addKeyboard(Update update, SendMessage responseMessage);


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


    protected InlineKeyboardMarkup createInlineKeyboard(List<Map<String, String>> buttons) {
        // Создаем список для рядов кнопок
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (Map<String, String> row : buttons) {
            // Создаем список для кнопок в текущем ряду
            List<InlineKeyboardButton> buttonRow = new ArrayList<>();
            // Проходим по каждой строке в ряду
            for (Map.Entry<String, String> map : row.entrySet()) {
                // Создаем кнопку
                InlineKeyboardButton button = new InlineKeyboardButton(map.getKey());
                // Устанавливаем callbackData (например, текст кнопки)
                button.setCallbackData(map.getValue());
                // Добавляем кнопку в ряд
                buttonRow.add(button);
            }
            // Добавляем ряд кнопок в клавиатуру
            keyboard.add(buttonRow);
        }
        // Создаем InlineKeyboardMarkup и устанавливаем клавиатуру
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(keyboard);

        return markup;
    }

}
