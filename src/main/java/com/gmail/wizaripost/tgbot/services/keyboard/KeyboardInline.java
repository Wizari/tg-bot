package com.gmail.wizaripost.tgbot.services.keyboard;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KeyboardInline extends AbstractKeyboard {
    @Override
    public SendMessage addKeyboard(Update update, SendMessage responseMessage) {

        List<Map<String, String>> buttons = new ArrayList<>();
        buttons.add(Map.of(
                "Start", "/start",
                "Weather", "/weather"));
        buttons.add(Map.of(
                "Photo", "/photo",
                "/Qbanner", "/Qbanner"));
        buttons.add(Map.of(
                "/removeLocation_4", "/removeLocation_4",
                "-", "-"));
        buttons.add(Map.of(
                "1", "qpage_1",
                "2", "qpage_2"));

            responseMessage.enableMarkdown(true);
            responseMessage.setReplyMarkup(this.createInlineKeyboard(buttons));
        return responseMessage;
    }
}
