package com.gmail.wizaripost.tgbot.services.keyboard;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Service
public class KeyboardOne extends AbstractKeyboard {
    @Override
    public SendMessage addKeyboard(Update update, SendMessage responseMessage) {
        List<List<String>> buttons = new ArrayList<>();
        buttons.add(List.of("/start", "/testInline", "/location"));
        buttons.add(List.of("/r User1", "-"));
        buttons.add(List.of("/one", "/weather"));
//        buttons.add(List.of("<", "1", "2", "3", ">"));
        buttons.add(List.of("1", "2"));


        responseMessage.enableMarkdown(true);
        responseMessage.setReplyMarkup(this.assembleReplyKeyboardMarkup(buttons));

        return responseMessage;
    }
}
