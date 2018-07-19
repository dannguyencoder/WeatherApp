package com.example.os_vinhnq.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherAPIEndpointInterface {

    //Request method and URL specified in the annotaion

    @GET("weather")
    Call<WeatherInfo> getWeather(@Query("id") int cityId, @Query("appid") String apiKey);
}
