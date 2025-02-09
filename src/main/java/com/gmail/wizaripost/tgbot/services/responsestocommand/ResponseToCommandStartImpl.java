package com.gmail.wizaripost.tgbot.services.responsestocommand;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class ResponseToCommandStartImpl implements IResponseToCommand {

    @Override
    public String generate(Update update) {
        return "Hello!";
    }

    @Override
    public String getTeg() {
        return "/start";
    }
}
