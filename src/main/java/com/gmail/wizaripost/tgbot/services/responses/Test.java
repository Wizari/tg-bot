package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Test extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        InlineKeyboardButton button1 = new InlineKeyboardButton();
        button1.setText("Новая кнопка 1");
        button1.setCallbackData("new_callback_1");

        InlineKeyboardButton button2 = new InlineKeyboardButton();
        button2.setText("Новая кнопка 2");
        button2.setCallbackData("new_callback_2");

        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup();
        inlineKeyboard.setKeyboard(Arrays.asList(
                Arrays.asList(button1),
                Arrays.asList(button2)
        ));

        EditMessageReplyMarkup editMarkup = new EditMessageReplyMarkup();
        editMarkup.setChatId(update.getMessage().getChatId());
        editMarkup.setMessageId(update.getMessage().getMessageId());
        editMarkup.setReplyMarkup(inlineKeyboard);

//        List<List<String>> buttons = new ArrayList<>();
//        buttons.add(List.of("1"));
//        buttons.add(List.of("2"));
//        SendMessage responseMessage = new SendMessage();
//        responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
//        responseMessage.enableMarkdown(true);
//        responseMessage.setText("Hello!");

        ResponseEntity response = new ResponseEntity();
        response.setResponse(editMarkup);
        return response;
    }


    @Override
    public String getTeg() {
        return "/test2";
    }
}
