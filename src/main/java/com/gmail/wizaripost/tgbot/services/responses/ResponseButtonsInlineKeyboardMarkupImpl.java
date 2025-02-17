package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseButtonsInlineKeyboardMarkupImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        List<List<String>> buttons = new ArrayList<>();
        buttons.add(List.of("/start", "когда ты обновлялась?", "когда ты обновлялась?"));
        buttons.add(List.of("/r User1", "Hello"));
        buttons.add(List.of("One", "Привет"));
        buttons.add(List.of("<", "1", "2", "3", ">"));

        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
//        responseMessage.enableMarkdown(true);
        responseMessage.setReplyMarkup(createInlineKeyboard(buttons));

        responseMessage.setText("Hello!");

        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        return response;
    }


    @Override
    public String getTeg() {
//        return "InlineKeyboardMarkup";
        return "/test1";
    }

    public InlineKeyboardMarkup createInlineKeyboard(List<List<String>> buttons) {
        // Создаем список для рядов кнопок
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        // Проходим по каждому ряду
        for (List<String> row : buttons) {
            // Создаем список для кнопок в текущем ряду
            List<InlineKeyboardButton> buttonRow = new ArrayList<>();
            // Проходим по каждой строке в ряду
            for (String buttonText : row) {
                // Создаем кнопку
                InlineKeyboardButton button = new InlineKeyboardButton(buttonText);
                // Устанавливаем callbackData (например, текст кнопки)
//                button.setCallbackData(buttonText);
                button.setCallbackData("page_"+buttonText);
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
