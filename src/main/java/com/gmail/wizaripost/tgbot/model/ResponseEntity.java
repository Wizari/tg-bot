package com.gmail.wizaripost.tgbot.model;

import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

@Data
public class ResponseEntity {

    public ResponseEntity() {
    }

    public ResponseEntity(PartialBotApiMethod response) {
        this.response = response;
    }

    public ResponseEntity(PartialBotApiMethod response, boolean deleteMessage) {
        this.response = response;
        this.deleteMessage = deleteMessage;
    }

    //    private BotApiMethod response;
    private PartialBotApiMethod response;
    private boolean deleteMessage = false;
}
