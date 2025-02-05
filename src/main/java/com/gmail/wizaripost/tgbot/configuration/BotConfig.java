package com.gmail.wizaripost.tgbot.configuration;

import com.gmail.wizaripost.tgbot.services.ConnectionLongPolling;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(ConnectionLongPolling telegramLongPolling) throws Exception {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(telegramLongPolling);
        return botsApi;
    }
}
