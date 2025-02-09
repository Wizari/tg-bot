package com.gmail.wizaripost.tgbot.services.responsestocommand;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface IResponseToCommand {

    String generate(Update update);

    String getTeg();

    default boolean postfixAllowed() {
        return false;
    }
    //для кнопок
    default String chatState() {
        return "IDLE";
    }
}