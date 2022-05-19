package com.challenge.LocoWeather.data.source.remote

import androidx.lifecycle.MutableLiveData
import com.challenge.LocoWeather.presentation.WeatherResponse

interface RemoteDataSource {
    suspend fun getWeatherInfoByLocation(location: String)
    val weatherResponse: MutableLiveData<WeatherResponse>
}
