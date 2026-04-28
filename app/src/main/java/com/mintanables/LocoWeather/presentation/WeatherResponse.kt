package com.mintanables.LocoWeather.presentation

import com.mintanables.LocoWeather.domain.model.Weather

sealed class WeatherResponse{
    data class Error(val errorMessage: String): WeatherResponse()
    data class Success(val weather: Weather): WeatherResponse()
}
