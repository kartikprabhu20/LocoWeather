package com.mintanables.LocoWeather.data.repository

import androidx.lifecycle.MutableLiveData
import com.mintanables.LocoWeather.data.source.remote.WeatherRemoteSource
import com.mintanables.LocoWeather.domain.repository.WeatherRepository
import com.mintanables.LocoWeather.presentation.WeatherResponse
import javax.inject.Inject

/***
 * Class to manage data. For the current usecase only a remote source is used, but the same can be saved in local room database and injected in this class.
 */
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