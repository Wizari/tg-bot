package com.gmail.wizaripost.tgbot.services.responsestocommand;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

//todo test. можно удалить
@Service
public class ResponseToCommandHelloImpl implements IResponseToCommand {
    @Override
    public String generate(Update update) {
        return "test \"Hello World\"";
    }

    @Override
    public String getTeg() {
        return "Hello";
    }
}
