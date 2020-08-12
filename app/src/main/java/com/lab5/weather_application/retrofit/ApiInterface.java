package com.lab5.weather_application.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("weather?q=Karachi&appid=5daba6c30fbdcace9d2266132d9cae50")
    Call<Example> getWeatherData(@Query("q") String name);
}
