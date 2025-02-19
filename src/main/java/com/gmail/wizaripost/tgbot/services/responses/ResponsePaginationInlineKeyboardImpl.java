package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import com.gmail.wizaripost.tgbot.util.CreatePaginationKeyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponsePaginationInlineKeyboardImpl extends AbstractResponse {

    @Autowired
    CreatePaginationKeyboard keyboard;

    @Override
    public ResponseEntity generateSendMessage(Update update) {
        String callbackData = update.getCallbackQuery().getData();
//        System.out.println("generateSendMessage, callbackData: "+ callbackData);
        int page = Integer.parseInt(callbackData.split("_")[1]);
        int totalPages = 5; // Общее количество страниц

        // Обновление сообщения с новой клавиатурой
        EditMessageText editMessage = new EditMessageText();
        editMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        editMessage.setText("Страница " + page + " из " + totalPages);
        editMessage.setReplyMarkup(keyboard.create(page, totalPages));

        ResponseEntity response = new ResponseEntity();
        response.setResponse(editMessage);
        return response;
    }

    @Override
    public String getTeg() {
        return "qpage_";
    }
}

