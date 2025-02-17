package com.gmail.wizaripost.tgbot.services;

import com.gmail.wizaripost.tgbot.services.responses.ResponsePaginationKeyboardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
@PropertySource("classpath:secrets.properties")
public class ConnectionLongPolling extends TelegramLongPollingBot {


    private final MainMessageController mainMessageController;
    @Value("${telegram.botName}")
    private String botUsername;

    @Autowired
    public ConnectionLongPolling(@Value("${telegram.token}") String botToken,
                                 MainMessageController mainMessageController
    ) {

        super(botToken);
        this.mainMessageController = mainMessageController;
    }

    @Override
    public void onUpdateReceived(Update update) {
//        System.out.println("Получено обновление1: " + update);
        if (update.hasMessage() && update.getMessage().hasText()) {
//            System.out.println("Получено сообщение2: " + update.getMessage().getText());
            try {
                execute(mainMessageController.getReply(update).getResponse());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
//            if (mainMessageController.getReply(update).isDeleteMessage()) {
//                try {
//                    deleteMessage(update.getMessage().getChatId(),
//                            update.getMessage().getMessageId());
//                } catch (TelegramApiException e) {
//                    throw new RuntimeException(e);
//                }
//            }


            if (update.hasCallbackQuery()) {
                System.out.println("callback" + update.getCallbackQuery().getData());
//                String callbackData = update.getCallbackQuery().getData();
//                if (callbackData.startsWith("page_")) {
//                    int page = Integer.parseInt(callbackData.split("_")[1]);
//                    int totalPages = 5; // Общее количество страниц
//
//                    // Обновление сообщения с новой клавиатурой
//                    EditMessageText editMessage = new EditMessageText();
//                    editMessage.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
//                    editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
//                    editMessage.setText("Страница " + page + " из " + totalPages);
//                    ResponsePaginationKeyboardImpl responsePaginationKeyboard = new ResponsePaginationKeyboardImpl();
//                    editMessage.setReplyMarkup(responsePaginationKeyboard.createPaginationKeyboard(page, totalPages));
//
//                    try {
//                        execute(editMessage);
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }

    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    public void deleteMessage(Long chatId, Integer messageId) throws TelegramApiException {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId.toString());
        deleteMessage.setMessageId(messageId);
        execute(deleteMessage);
    }
}
