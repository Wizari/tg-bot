package com.gmail.wizaripost.tgbot.model;


import lombok.Data;

@Data
public class CurrentUnits {
    private String time;
    private String interval;
    private String temperature_2m;
    private String wind_speed_10m;
    private String weather_code;
}
