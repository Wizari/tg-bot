package com.gmail.wizaripost.tgbot.util;

import com.gmail.wizaripost.tgbot.model.AppState;

public enum States {
    INSTANCE;

    private AppState state = AppState.IDLE;

    public AppState getState() {
        return state;
    }
    public String getStatePrefix() {
        if (state == AppState.IDLE) {
            return "";
        }
        return state.toString();
    }

    public void setState(AppState state) {
        this.state = state;
    }
}

