package com.gmail.wizaripost.tgbot.util;

import com.gmail.wizaripost.tgbot.model.AppState;

import java.util.HashMap;
import java.util.Map;

public enum States {
    INSTANCE,
    ;

    //    private AppState state = AppState.IDLE;
    private static final Map<Long, AppState> CHATS = new HashMap<>();


    public AppState getState(Long chatId) {
        return CHATS.get(chatId);
    }

    public String getStatePrefix(Long chatId) {
        CHATS.putIfAbsent(chatId, AppState.IDLE);
        if (CHATS.get(chatId) == AppState.IDLE) {
            return "";
        }
        return CHATS.get(chatId).toString();
    }

    public void setState(Long chatId, AppState state) {
        CHATS.put(chatId, state);
    }

    public Map<Long, AppState> getChats() {
        return CHATS;
    }

}

