package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.services.keyboard.KeyboardOne;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseButtonsOneTimeLocationImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(getChatId(update));
        responseMessage.enableMarkdown(true);
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        KeyboardRow row = new KeyboardRow();

        // Добавляем кнопку в строку
        KeyboardButton button = new KeyboardButton();
        button.setText("Отправить местоположение");
        button.setRequestLocation(true);
        row.add(button);

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row);
        markup.setKeyboard(keyboard);
        markup.setOneTimeKeyboard(true);

        responseMessage.setReplyMarkup(markup);
        responseMessage.setText("Мeстоположение");

        return new ResponseEntity(responseMessage, true);
    }


    @Override
    public String getTeg() {
        return "/location";
    }
}
