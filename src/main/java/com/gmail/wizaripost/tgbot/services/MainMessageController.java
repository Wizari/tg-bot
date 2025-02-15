package com.gmail.wizaripost.tgbot.services;

import com.gmail.wizaripost.tgbot.services.responses.AbstractResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Set;


@Service
public class MainMessageController {

    //    @Autowired
//    private Set<IResponseToCommand> responseToCommands;
//    @Autowired
//    private Set<AbstractButtons> buttons;
    @Autowired
    private Set<AbstractResponse> response;
    private final TelegramSynchronizedService telegramSynchronizedService;
//    private static final Map<Long, ChatState> CHATS = new HashMap<>();


    public MainMessageController(
            TelegramSynchronizedService telegramSynchronizedService
    ) {
        this.telegramSynchronizedService = telegramSynchronizedService;
    }

    //    public SendMessage getReply(Update update) {
    public BotApiMethod getReply(Update update) {
        synchronized (telegramSynchronizedService.getMessageLock(update.getMessage().getFrom().getId())) {
            for (AbstractResponse res : this.response) {
                if (res.getTeg().equals(update.getMessage().getText())) {
                    return res.generateSendMessage(update);
                }
            }


//Error: Incorrect command
            for (AbstractResponse res : this.response) {
                if (res.getTeg().equals("error")) {
                    return res.generateSendMessage(update);
                }
            }
//Error: Incorrect command
            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            responseMessage.setText("Incorrect command");
            return responseMessage;


            //            var message = update.getMessage();
//            var userId = message.getFrom().getId();
//            var chatId = update.getMessage().getChatId();
//            var messageText = update.getMessage().getText();
//
//            CHATS.putIfAbsent(chatId, ChatState.IDLE);
//            ChatState currentChat = CHATS.get(chatId);
//            //todo
//
//
//            SendMessage responseMessage = new SendMessage();
//            responseMessage.setChatId(String.valueOf(chatId));
//
//            for (IResponseToCommand mes : this.responseToCommands) {
//                if (mes.getTeg().equals(messageText)) {
//                    responseMessage.setText(mes.generate(update));
//                    currentChat = mes.getChatState();
//                    //todo добавить кнопки(setReplyMarkup)
//                    for (AbstractButtons button : this.buttons) {
//                        if (button.getTeg().toString().equals(mes.getChatState().toString())) {
//                            responseMessage.enableMarkdown(true);
//                            responseMessage.setReplyMarkup(button.generate());
//                        }
//                    }
//                    return responseMessage;
//                }
//            }
//            responseMessage.setText("Incorrect command");
//            return responseMessage;


        }
    }
}
