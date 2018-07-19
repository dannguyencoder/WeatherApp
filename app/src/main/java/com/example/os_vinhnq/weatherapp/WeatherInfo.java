package com.example.os_vinhnq.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherInfo {

    @SerializedName("weather")
    public List<Weather> weather;

    public class Weather {

        @SerializedName("main")
        public String weatherMain;

        @SerializedName("description")
        public String description;
    }
}
