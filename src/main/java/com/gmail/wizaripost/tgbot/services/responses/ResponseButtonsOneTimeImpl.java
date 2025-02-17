package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseButtonsOneTimeImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        List<List<String>> buttons = new ArrayList<>();
        buttons.add(List.of("1"));
        buttons.add(List.of("2"));

        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        responseMessage.enableMarkdown(true);
        ReplyKeyboardMarkup markup = this.assembleReplyKeyboardMarkup(buttons);
        markup.setOneTimeKeyboard(true);
        responseMessage.setReplyMarkup(markup);

        responseMessage.setText("Hello!");

        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        return response;
    }


    @Override
    public String getTeg() {
        return "/one";
    }
}
