package com.gmail.wizaripost.tgbot.services;

import com.gmail.wizaripost.tgbot.model.WeatherResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(double latitude, double longitude) {
        String url = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%.2f&longitude=%.2f&current=temperature_2m,wind_speed_10m,weather_code&timezone=Europe/Moscow",
                latitude, longitude
        );

        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
