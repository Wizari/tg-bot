package com.gmail.wizaripost.tgbot.model;

import lombok.Data;


@Data
public class WeatherResponse {
    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private CurrentUnits current_units;
    private CurrentWeather current;
}

