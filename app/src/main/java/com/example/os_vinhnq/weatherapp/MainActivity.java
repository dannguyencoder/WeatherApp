package com.example.os_vinhnq.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final int CITY_ID = 1581129;
    private static final String API_KEY = "df2c408b656ab0b89cd06f48af12c7f5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callApi();
    }

    //Trailing slash is needed
    public static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/";

    private void callApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherAPIEndpointInterface weatherAPIService = retrofit.create(WeatherAPIEndpointInterface.class);
        Call<WeatherInfo> weatherInfoCall = weatherAPIService.getWeather(CITY_ID, API_KEY);
        weatherInfoCall.enqueue(new Callback<WeatherInfo>() {
            @Override
            public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                int statusCode = response.code();
                WeatherInfo weatherInfo = response.body();
                Log.d("Weather_info", statusCode + " " + weatherInfo.weather.get(0).weatherMain);
                Toast.makeText(MainActivity.this, statusCode + " " + weatherInfo.weather.get(0).weatherMain, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<WeatherInfo> call, Throwable t) {
                Log.d("Weather_info", call.toString() + " " + t.getMessage());
                //Log error here since request failed
            }
        });
    }
}
