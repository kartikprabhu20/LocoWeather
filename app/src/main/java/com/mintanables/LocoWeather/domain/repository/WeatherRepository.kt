package com.mintanables.LocoWeather.domain.repository

import androidx.lifecycle.MutableLiveData
import com.mintanables.LocoWeather.presentation.WeatherResponse

interface WeatherRepository {
    suspend fun getWeather(location: String)
    val weatherResponse: MutableLiveData<WeatherResponse>
}