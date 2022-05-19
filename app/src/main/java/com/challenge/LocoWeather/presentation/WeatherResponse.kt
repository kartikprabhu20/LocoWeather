package com.challenge.LocoWeather.presentation

import com.challenge.LocoWeather.domain.model.Weather

sealed class WeatherResponse{
    data class Error(val errorMessage: String): WeatherResponse()
    data class Success(val weather: Weather): WeatherResponse()
}
