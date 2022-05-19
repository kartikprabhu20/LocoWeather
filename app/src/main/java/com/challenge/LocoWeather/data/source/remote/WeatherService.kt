package com.challenge.LocoWeather.data.source.remote

import com.challenge.LocoWeather.domain.model.Weather
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    @GET("{location}")
    fun getWeatherInfoByLocation(
        @Path("location") location: String
    ): Call<Weather>
}
