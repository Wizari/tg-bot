package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResponseButtonsOneImpl extends AbstractResponse {
    @Override
    public ResponseEntity generateSendMessage(Update update) {
        List<List<String>> buttons = new ArrayList<>();
        buttons.add(List.of("/start", "/testInline", "/location"));
        buttons.add(List.of("/r User1", "Hello"));
        buttons.add(List.of("/one", "/weather"));
//        buttons.add(List.of("<", "1", "2", "3", ">"));
        buttons.add(List.of("1", "2"));

        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        responseMessage.enableMarkdown(true);
        responseMessage.setReplyMarkup(this.assembleReplyKeyboardMarkup(buttons));

        responseMessage.setText("Hello!");


        ResponseEntity response = new ResponseEntity();
        response.setResponse(responseMessage);
        response.setDeleteMessage(true);
        return response;
    }


    @Override
    public String getTeg() {
        return "1";
    }
}
