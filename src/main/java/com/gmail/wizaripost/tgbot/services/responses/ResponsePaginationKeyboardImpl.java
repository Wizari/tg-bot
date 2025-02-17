package com.gmail.wizaripost.tgbot.services.responses;

import com.gmail.wizaripost.tgbot.model.ResponseEntity;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResponsePaginationKeyboardImpl  {

//    public InlineKeyboardMarkup createPaginationKeyboard(int currentPage, int totalPages) {
//        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
//
//        if (currentPage > 1) {
//            keyboard.add(Collections.singletonList(
//                    InlineKeyboardButton.builder()
//                            .text("⬅️ Назад")
//                            .callbackData("page_" + (currentPage - 1))
//                            .build()
//            ));
//        }
//
//        if (currentPage < totalPages) {
//            keyboard.add(Collections.singletonList(
//                    InlineKeyboardButton.builder()
//                            .text("Вперед ➡️")
//                            .callbackData("page_" + (currentPage + 1))
//                            .build()
//            ));
//        }
//        InlineKeyboardMarkup res = InlineKeyboardMarkup.builder().keyboard(keyboard).build();
////        SendMessage responseMessage = new SendMessage();
////        responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
//////        responseMessage.enableMarkdown(true);
////        responseMessage.setReplyMarkup(res);
////
////        responseMessage.setText("Hello!");
////        ResponseEntity response = new ResponseEntity();
////        response.setResponse(responseMessage);
//        return res;
//
//    }
//
//
//
//    public String getTeg() {
//        return null;
//    }
}
