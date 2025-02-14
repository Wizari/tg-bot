package com.gmail.wizaripost.tgbot.services;

import com.gmail.wizaripost.tgbot.model.ChatState;
import com.gmail.wizaripost.tgbot.services.buttons.AbstractButtons;
import com.gmail.wizaripost.tgbot.services.responsestocommand.IResponseToCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Service
public class MainMessageController {

    @Autowired
    private Set<IResponseToCommand> responseToCommands;
    @Autowired
    private Set<AbstractButtons> buttons;
    private final TelegramSynchronizedService telegramSynchronizedService;
    private static final Map<Long, ChatState> CHATS = new HashMap<>();


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

            CHATS.putIfAbsent(chatId, ChatState.IDLE);
            ChatState currentChat = CHATS.get(chatId);
            //todo


            SendMessage responseMessage = new SendMessage();
            responseMessage.setChatId(String.valueOf(chatId));

            for (IResponseToCommand mes : this.responseToCommands) {
                if (mes.getTeg().equals(messageText)) {
                    responseMessage.setText(mes.generate(update));
                    currentChat = mes.getChatState();
                    //todo добавить кнопки(setReplyMarkup)
                    for (AbstractButtons button : this.buttons) {
                        if (button.getTeg().toString().equals(mes.getChatState().toString())) {
                            responseMessage.enableMarkdown(true);
                            responseMessage.setReplyMarkup(button.generate());
                        }
                    }
                    return responseMessage;
                }
            }
            responseMessage.setText("Incorrect command");
            return responseMessage;
        }
    }
}
