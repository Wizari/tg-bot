package com.gmail.wizaripost.tgbot.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
public class MainMessageController {

    private final TelegramSynchronizedService telegramSynchronizedService;
//    private final ResponseBodyBuilder responseBodyBuilder;
//    private final ResponseMessageBuilder responseMessageBuilder;

    public MainMessageController(
            TelegramSynchronizedService telegramSynchronizedService
//            ResponseBodyBuilder responseBodyBuilder,
//            ResponseMessageBuilder responseMessageBuilder
    ) {
        this.telegramSynchronizedService = telegramSynchronizedService;
//        this.responseBodyBuilder = responseBodyBuilder;
//        this.responseMessageBuilder = responseMessageBuilder;
    }

    public SendMessage getReply(Update update) {
        synchronized (telegramSynchronizedService.getMessageLock(update.getMessage().getFrom().getId())) {
            var message = update.getMessage();
            var userId = message.getFrom().getId();
            var chatId = update.getMessage().getChatId();

            String messageText = update.getMessage().getText();
//            long chatId = update.getMessage().getChatId();

            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(String.valueOf(chatId));
            responseMessage.setText("Вы сказали: " + messageText);





//            var responseText = responseBodyBuilder.responseBody(userId, chatId, message);
//            var responseMessage = responseMessageBuilder.buildMessage(chatId, responseText);

//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            return responseMessage;
        }
    }
}