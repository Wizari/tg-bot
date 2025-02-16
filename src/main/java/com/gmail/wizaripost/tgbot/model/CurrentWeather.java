package com.gmail.wizaripost.tgbot.model;

import lombok.Data;

@Data
public class CurrentWeather {
    private String time;
    private int interval;
    private double temperature_2m;
    private double wind_speed_10m;
    private int weather_code;
}
