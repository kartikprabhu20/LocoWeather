package com.mintanables.LocoWeather.data.source.remote

import com.mintanables.LocoWeather.data.source.remote.dto.CurrentWeatherDto
import com.mintanables.LocoWeather.data.source.remote.dto.ForecastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @retrofit2.http.QueryMap options: Map<String, String>,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Response<CurrentWeatherDto>

    @GET("forecast")
    suspend fun getForecast(
        @retrofit2.http.QueryMap options: Map<String, String>,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): Response<ForecastDto>
}
