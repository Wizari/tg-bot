package com.gmail.wizaripost.tgbot.services;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TelegramSynchronizedService {

    private final Map<Long, Object> messageLocks = new ConcurrentHashMap<>();

    public Object getMessageLock(Update update) {
        synchronized (messageLocks) {
            Long messageId = null;
            if (update.hasMessage() && update.getMessage().hasText()) {
                messageId = update.getMessage().getFrom().getId();
            } else if (update.hasCallbackQuery()) {
                messageId = update.getCallbackQuery().getFrom().getId();
            } else if (update.hasMessage() && update.getMessage().hasLocation()) {
                messageId = update.getMessage().getFrom().getId();
            }


//            System.out.println("11: "+update.getMessage().getFrom().getId());
//            Long messageId1 = update.getMessage().getFrom().getId();
////            Long messageId2 = update.getCallbackQuery().getFrom().getId();
//            Long messageId = (messageId1 != null) ? messageId1 : update.getCallbackQuery().getFrom().getId();
//            Long messageId = null;
//            if (messageId1 != null) {
//                messageId = (Long) messageId1;
//            } else if (messageId2 != null) {
//                messageId = (Long) messageId2;
//            } else {
//                System.out.println("synchronized error! {messageId = null}");
//            }
//            Long messageId = null;
//            try {
//                System.out.println("11: " + update.getMessage().getFrom().getId());
//                Long messageId1 = update.getMessage().getFrom().getId();
////            Long messageId2 = update.getCallbackQuery().getFrom().getId();
//                messageId = (messageId1 != null) ? messageId1 : update.getCallbackQuery().getFrom().getId();
//            } catch (NullPointerException e) {
//            }
//            if (messageId == null) {
//                try {
//                    messageId = update.getCallbackQuery().getFrom().getId();
//                } catch (NullPointerException e) {
//                }
//            }


            Object lock = messageLocks.get(messageId);
            if (lock == null) {
                lock = new Object();
                messageLocks.put(messageId, lock);
            }
            return lock;
        }
    }
}
