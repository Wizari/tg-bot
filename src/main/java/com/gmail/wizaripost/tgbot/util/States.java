package com.gmail.wizaripost.tgbot.util;

import com.gmail.wizaripost.tgbot.model.AppState;

public enum States {
    INSTANCE;

    private AppState state;

    public AppState getState() {
        return state;
    }

    public void setState(AppState state) {
        this.state = state;
    }
}

