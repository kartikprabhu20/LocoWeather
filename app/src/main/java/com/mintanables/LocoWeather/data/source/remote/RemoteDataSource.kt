package com.mintanables.LocoWeather.data.source.remote

import androidx.lifecycle.MutableLiveData
import com.mintanables.LocoWeather.presentation.WeatherResponse

interface RemoteDataSource {
    suspend fun getWeatherInfoByLocation(location: String)
    val weatherResponse: MutableLiveData<WeatherResponse>
}
