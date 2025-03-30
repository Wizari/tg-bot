package com.gmail.wizaripost.tgbot.util;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class DelayService {

    @Async  // Требует включения @EnableAsync в конфигурации
    public CompletableFuture<Void> delayAsync(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return CompletableFuture.completedFuture(null);
    }
}
