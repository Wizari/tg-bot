package com.gmail.wizaripost.tgbot.services;

import com.gmail.wizaripost.tgbot.services.responsestocommand.IResponseToCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;


@Service
public class MainMessageController {

    @Autowired
    private Set<IResponseToCommand> responseToCommands;
    private final TelegramSynchronizedService telegramSynchronizedService;

    public MainMessageController(
            TelegramSynchronizedService telegramSynchronizedService
    ) {
        this.telegramSynchronizedService = telegramSynchronizedService;
    }

    public SendMessage getReply(Update update) {
        synchronized (telegramSynchronizedService.getMessageLock(update.getMessage().getFrom().getId())) {
            var message = update.getMessage();
            var userId = message.getFrom().getId();
            var chatId = update.getMessage().getChatId();
            var messageText = update.getMessage().getText();

            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(String.valueOf(chatId));

            for (IResponseToCommand mes : this.responseToCommands) {
                if (mes.getTeg().equals(messageText)) {
                    responseMessage.setText(mes.generate(update));
                    //todo добавить кнопки(setReplyMarkup)
//                    responseMessage.enableMarkdown(true);
//                    responseMessage.setReplyMarkup();

                    return responseMessage;
                }
            }
            responseMessage.setText("Incorrect command");
            return responseMessage;
        }
    }
}
