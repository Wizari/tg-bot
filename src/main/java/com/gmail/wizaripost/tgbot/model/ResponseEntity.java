package com.gmail.wizaripost.tgbot.model;

import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

@Data
public class ResponseEntity {
    //    private BotApiMethod response;
    private PartialBotApiMethod response;
    private boolean deleteMessage = false;
}
