package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ResponseButtonsOneTimeImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
//        List<List<String>> buttons = new ArrayList<>();
//        buttons.add(List.of("1"));
//        buttons.add(List.of("2"));

        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.enableMarkdown(true);
//        ReplyKeyboardMarkup markup = this.assembleReplyKeyboardMarkup(buttons);
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        KeyboardRow row = new KeyboardRow();

        // Добавляем кнопку в строку
        KeyboardButton button = new KeyboardButton();
        button.setText("Отправить местоположение");
//        button.getRequestLocation();
        button.setRequestLocation(true);
        row.add(button);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);
        markup.setKeyboard(keyboard);
        markup.setOneTimeKeyboard(true);

        responseMessage.setReplyMarkup(markup);
        responseMessage.setText("Мeстоположение");

        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        return response;
    }


    @Override
    public String getTeg() {
        return "/one";
    }
}
