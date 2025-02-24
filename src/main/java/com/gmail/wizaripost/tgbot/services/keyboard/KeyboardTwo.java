package com.gmail.wizaripost.tgbot.services.keyboard;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyboardTwo extends AbstractKeyboard {
    @Override
    public SendMessage addKeyboard(Update update, SendMessage responseMessage) {
        List<List<String>> buttons = new ArrayList<>();
        buttons.add(List.of("/start", "/photo", "/photo2"));
        buttons.add(List.of("/r User1", "-"));
        buttons.add(List.of("Two", "-"));
//        buttons.add(List.of("<", "1", "2", "3", ">"));
        buttons.add(List.of("1", "2"));


        responseMessage.enableMarkdown(true);
        responseMessage.setReplyMarkup(this.assembleReplyKeyboardMarkup(buttons));

        return responseMessage;
    }
}
