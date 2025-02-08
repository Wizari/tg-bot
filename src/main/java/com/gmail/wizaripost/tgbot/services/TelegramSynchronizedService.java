package com.gmail.wizaripost.tgbot.services;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TelegramSynchronizedService {

    private final Map<Long, Object> messageLocks = new ConcurrentHashMap<>();

    public Object getMessageLock(Long messageId) {
        synchronized (messageLocks) {
            Object lock = messageLocks.get(messageId);
            if (lock == null) {
                lock = new Object();
                messageLocks.put(messageId, lock);
            }
            return lock;
        }
    }
}
