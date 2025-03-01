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

            Object lock = messageLocks.get(messageId);
            if (lock == null) {
                lock = new Object();
                messageLocks.put(messageId, lock);
            }
            return lock;
        }
    }
}
