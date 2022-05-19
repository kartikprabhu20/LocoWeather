package com.challenge.LocoWeather.domain.repository

import androidx.lifecycle.MutableLiveData
import com.challenge.LocoWeather.data.source.remote.WeatherService
import com.challenge.LocoWeather.presentation.WeatherResponse
import javax.inject.Inject

interface WeatherRepository {
    suspend fun getWeather(location: String)
    val weatherResponse: MutableLiveData<WeatherResponse>
}