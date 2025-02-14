package com.gmail.wizaripost.tgbot.services.responses;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseButtonsOneImpl extends AbstractResponse {
    @Override
    public SendMessage generateSendMessage(Update update) {
        List<List<String>> buttons = new ArrayList<>();
        buttons.add(List.of("/start", "когда ты обновлялась?", "когда ты обновлялась?"));
        buttons.add(List.of("/r User1", "Hello"));
        buttons.add(List.of("One", "Привет"));
        buttons.add(List.of("<", "1", "2", "3", ">"));

        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        responseMessage.enableMarkdown(true);
        responseMessage.setReplyMarkup(this.assembleReplyKeyboardMarkup(buttons));

        responseMessage.setText("Hello!");


        return responseMessage;
    }


    @Override
    public String getTeg() {
        return "1";
    }
}
