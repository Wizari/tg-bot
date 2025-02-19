package com.gmail.wizaripost.tgbot.util;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class CreatePaginationKeyboard {

    public InlineKeyboardMarkup create(int currentPage, int totalPages) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        if (currentPage > 1) {
            keyboard.add(Collections.singletonList(
                    InlineKeyboardButton.builder()
                            .text("⬅️ Назад")
                            .callbackData("qpage_" + (currentPage - 1))
                            .build()
            ));
        }

        if (currentPage < totalPages) {
            keyboard.add(Collections.singletonList(
                    InlineKeyboardButton.builder()
                            .text("Вперед ➡️")
                            .callbackData("qpage_" + (currentPage + 1))
                            .build()
            ));
        }
        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();

    }
}
