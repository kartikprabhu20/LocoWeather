package com.challenge.LocoWeather.data.repository

import androidx.lifecycle.MutableLiveData
import com.challenge.LocoWeather.data.source.remote.WeatherRemoteSource
import com.challenge.LocoWeather.data.source.remote.WeatherService
import com.challenge.LocoWeather.domain.repository.WeatherRepository
import com.challenge.LocoWeather.presentation.WeatherResponse
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private  val weatherRemoteSource: WeatherRemoteSource) : WeatherRepository {
    private val _weatherResponse = MutableLiveData<WeatherResponse>() // can be changed
    override val weatherResponse: MutableLiveData<WeatherResponse>
        get() = _weatherResponse

    init {
        weatherRemoteSource.weatherResponse.observeForever{
            weatherResponse.postValue(it)
        }
    }
    override suspend fun getWeather(location: String) {
            weatherRemoteSource.getWeatherInfoByLocation(location)
    }
}