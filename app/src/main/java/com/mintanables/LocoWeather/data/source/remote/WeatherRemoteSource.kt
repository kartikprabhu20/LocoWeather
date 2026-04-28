package com.mintanables.LocoWeather.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mintanables.LocoWeather.data.Constants.TAG
import com.mintanables.LocoWeather.domain.model.*
import com.mintanables.LocoWeather.presentation.WeatherResponse
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class WeatherRemoteSource @Inject constructor(private val weatherService: WeatherService) : RemoteDataSource {

    private val _weatherResponse = MutableLiveData<WeatherResponse>()
    override val weatherResponse: MutableLiveData<WeatherResponse>
        get() = _weatherResponse

    override suspend fun getWeatherInfoByLocation(location: String) {
        val latLon = location.split(",")
        val lat = if (latLon.isNotEmpty()) latLon[0] else ""
        val lon = if (latLon.size > 1) latLon[1] else ""
        val appId = com.mintanables.LocoWeather.data.Constants.API_KEY

        try {
            coroutineScope {
                val currentDeferred = async { weatherService.getCurrentWeather(lat, lon, appId, "imperial") }
                val forecastDeferred = async { weatherService.getForecast(lat, lon, appId, "imperial") }

                val currentRes = currentDeferred.await()
                val forecastRes = forecastDeferred.await()

                if (currentRes.isSuccessful && forecastRes.isSuccessful) {
                    val currentDto = currentRes.body()
                    val forecastDto = forecastRes.body()
                    
                    if (currentDto != null && forecastDto != null) {
                        // Map current
                        val currentObj = Current(
                            dt = currentDto.dt,
                            temp = currentDto.main?.temp,
                            feelsLike = currentDto.main?.feelsLike,
                            pressure = currentDto.main?.pressure,
                            humidity = currentDto.main?.humidity,
                            visibility = currentDto.visibility,
                            windSpeed = currentDto.wind?.speed,
                            windDeg = currentDto.wind?.deg,
                            weather = currentDto.weather
                        )

                        // Map hourly
                        val hourlyList = forecastDto.list?.map { item ->
                            HourlyItem(
                                dt = item.dt,
                                temp = item.main?.temp ?: 0.0,
                                feelsLike = item.main?.feelsLike ?: 0.0,
                                pressure = item.main?.pressure ?: 0.0,
                                humidity = item.main?.humidity ?: 0.0,
                                visibility = item.visibility,
                                windSpeed = item.wind?.speed ?: 0.0,
                                windDeg = item.wind?.deg ?: 0.0,
                                pop = item.pop,
                                weather = item.weather
                            )
                        } ?: emptyList()

                        // Map daily (Group by day string from dt_txt "YYYY-MM-DD")
                        val dailyList = forecastDto.list?.groupBy { it.dtTxt.substringBefore(" ") }?.map { (dateStr, itemsForDay) ->
                            val minTemp = itemsForDay.minOfOrNull { it.main?.tempMin ?: 0.0 } ?: 0.0
                            val maxTemp = itemsForDay.maxOfOrNull { it.main?.tempMax ?: 0.0 } ?: 0.0
                            val representative = itemsForDay.first()
                            
                            DailyItem(
                                dt = representative.dt,
                                temp = Temp(min = minTemp, max = maxTemp),
                                pressure = representative.main?.pressure ?: 0.0,
                                humidity = representative.main?.humidity ?: 0.0,
                                windSpeed = representative.wind?.speed ?: 0.0,
                                weather = representative.weather,
                                pop = itemsForDay.maxOfOrNull { it.pop } ?: 0.0
                            )
                        } ?: emptyList()

                        val weather = Weather(
                            lat = lat.toDoubleOrNull(),
                            lon = lon.toDoubleOrNull(),
                            current = currentObj,
                            hourly = hourlyList,
                            daily = dailyList
                        )
                        _weatherResponse.postValue(WeatherResponse.Success(weather))
                    } else {
                        _weatherResponse.postValue(WeatherResponse.Error("Empty payload from OpenWeatherMap"))
                    }
                } else {
                    val code = if (!currentRes.isSuccessful) currentRes.code() else forecastRes.code()
                    val error = if (!currentRes.isSuccessful) currentRes.errorBody()?.string() else forecastRes.errorBody()?.string()
                    Log.e(TAG, "getWeather HTTP Error $code: $error")
                    _weatherResponse.postValue(WeatherResponse.Error("HTTP Error $code: $error"))
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "getWeather Exception: \${e.message}")
            _weatherResponse.postValue(WeatherResponse.Error("Connection Exception: \${e.message}"))
        }
    }
}
