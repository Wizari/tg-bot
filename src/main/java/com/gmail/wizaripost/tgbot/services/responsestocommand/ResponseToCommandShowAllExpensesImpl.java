package com.gmail.wizaripost.tgbot.services.responsestocommand;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

//todo test. можно удалить
@Service
public class ResponseToCommandShowAllExpensesImpl implements IResponseToCommand {
    @Override
    public String generate(Update update) {
        return "test \"Показать все траты\"";
    }

    @Override
    public String getTeg() {
        return "Показать все траты";
    }
}
