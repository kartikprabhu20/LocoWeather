package com.mintanables.LocoWeather.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mintanables.LocoWeather.R
import com.mintanables.LocoWeather.Utils.formatIcons
import com.mintanables.LocoWeather.Utils.getFormattedDate
import com.mintanables.LocoWeather.Utils.getFormattedDateOnly
import com.mintanables.LocoWeather.Utils.getFormattedTime
import com.mintanables.LocoWeather.data.Constants.TAG
import com.mintanables.LocoWeather.data.repository.WeatherRepositoryImpl
import com.mintanables.LocoWeather.domain.model.DailyItem
import com.mintanables.LocoWeather.domain.model.HourlyItem
import com.mintanables.LocoWeather.domain.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepositoryImpl,
    private val settingsRepository: com.mintanables.LocoWeather.domain.repository.SettingsRepository
) : ViewModel(){

    private val _weather = MutableStateFlow(Weather())
    val weather : MutableStateFlow<Weather>
        get() = _weather

    var isLoading = mutableStateOf(true)
    var currentDate = mutableStateOf("")

    var currentSummary = mutableStateOf("")
    var currentTemperature = mutableStateOf("")
    var  currentImage = mutableStateOf("")
    var currentImageIcon = mutableStateOf(R.drawable.clear_day)
    var currentHumidity = mutableStateOf("")
    var currentPressure = mutableStateOf("")
    var currentVisibility = mutableStateOf("")
    var currentDewpoint = mutableStateOf("")

    val _hourlyList = MutableStateFlow<List<HourlyItem>>(emptyList())
    val hourlyList: StateFlow<List<HourlyItem>>
        get() = _hourlyList

    val _dailyList = MutableStateFlow<List<DailyItem>>(emptyList())
    val dailyList: StateFlow<List<DailyItem>>
        get() = _dailyList

    init {
        weatherRepository.weatherResponse.observeForever{
            when(it){
                is WeatherResponse.Success->{
                    Log.i(TAG,"[WeatherViewModel] " +it.weather.toString())
                    _weather.value = it.weather
                    val current = _weather.value.current
                    
                    val symbol = if (settingsRepository.getUnit() == "metric") "\u2103" else "\u2109"

                    currentDate.value = getFormattedDate(current?.dt)
                    currentSummary.value = current?.weather?.firstOrNull()?.description?.replaceFirstChar { it.uppercase() } ?: "--"
                    currentTemperature.value = String.format("%.2f %s", current?.temp, symbol)
                    currentPressure.value = String.format("%.2f hPa",current?.pressure )
                    currentVisibility.value = current?.visibility.toString()
                    currentHumidity.value =  String.format("%.2f %%",current?.humidity)
                    currentDewpoint.value =  String.format("%.2f %s",current?.dewPoint, symbol)
                    currentImageIcon.value = formatIcons(current?.weather?.firstOrNull()?.icon)
                    _dailyList.value = _weather.value.daily?.map{
                        it.apply {
                            temperatureLow = String.format(Locale.US, "%.2f", it.temp.min).toDouble()
                            temperatureHigh =  String.format(Locale.US, "%.2f", it.temp.max).toDouble()
                            date = getFormattedDateOnly(it.dt)
                            iconId = formatIcons(it.weather?.firstOrNull()?.icon)
                            this.unitSymbol = symbol
                        }
                    } ?: emptyList()

                    _hourlyList.value = _weather.value.hourly?.map{
                        it.apply {
                            temp = String.format(Locale.US, "%.2f", it.temp).toDouble()
                            formatedTime = getFormattedTime(it.dt)
                            iconId = formatIcons(it.weather?.firstOrNull()?.icon)
                            this.unitSymbol = symbol
                        }
                    } ?: emptyList()

                    isLoading.value = false
                }
                is WeatherResponse.Error ->{
                    Log.e(TAG,"[WeatherViewModel] "+it.errorMessage)
                    isLoading.value = false
                    currentSummary.value = "Error loading weather data."
                }
            }
        }
    }

    fun getWeatherInfoByLocation(location: String) = viewModelScope.launch(Dispatchers.IO) {
         weatherRepository.getWeather(location)
    }
}
