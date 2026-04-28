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
import com.mintanables.LocoWeather.domain.model.DailyData
import com.mintanables.LocoWeather.domain.model.HourlyData
import com.mintanables.LocoWeather.domain.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepositoryImpl) : ViewModel(){

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


    val _hourlyList = MutableStateFlow<List<HourlyData>>(emptyList())
    val hourlyList: StateFlow<List<HourlyData>>
        get() = _hourlyList

    val _dailyList = MutableStateFlow<List<DailyData>>(emptyList())
    val dailyList: StateFlow<List<DailyData>>
        get() = _dailyList

    init {
        weatherRepository.weatherResponse.observeForever{
            when(it){
                is WeatherResponse.Success->{
                    Log.i(TAG,"[WeatherViewModel] " +it.weather.toString())
                    _weather.value = it.weather
                    val currently = _weather.value.currently
                    currentDate.value = getFormattedDate(currently?.time)
                    currentSummary.value = currently?.summary ?: "--"
                    currentTemperature.value = String.format("%.2f", currently?.temperature)
                    currentPressure.value = String.format("%.2f",currently?.pressure )
                    currentVisibility.value = currently?.visibility.toString()
                    currentHumidity.value =  String.format("%.2f",currently?.humidity)
                    currentDewpoint.value =  String.format("%.2f",currently?.dewPoint)
                    currentImageIcon.value = formatIcons(currently?.icon)
                    _dailyList.value = _weather.value.daily?.data?.map{
                        it.apply {
                            temperatureLow = String.format("%.2f",it.temperatureLow).toDouble()
                            temperatureHigh =  String.format("%.2f",it.temperatureHigh).toDouble()
                            date = getFormattedDateOnly(it.time)
                            iconId = formatIcons(it.icon)
                        }
                    } ?: emptyList()

                    _hourlyList.value = _weather.value.hourly?.data?.map{
                        it.apply {
                            temperature = String.format("%.2f",it.temperature).toDouble()
                            formatedTime = getFormattedTime(it.time)
                            iconId = formatIcons(it.icon)
                        }
                    } ?: emptyList()

                    isLoading.value = false
                }
                is WeatherResponse.Error ->{
                    Log.e(TAG,"[WeatherViewModel] "+it.errorMessage)
                }
            }
        }
    }



    fun getWeatherInfoByLocation(location: String) = viewModelScope.launch(Dispatchers.IO) {
         weatherRepository.getWeather(location)
    }
}

