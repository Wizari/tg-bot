package com.gmail.wizaripost.tgbot.model;

import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@Data
public class ResponseEntity {
    private BotApiMethod response;
    private boolean deleteMessage = false;
}
