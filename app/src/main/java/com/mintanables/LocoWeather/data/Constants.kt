package com.mintanables.LocoWeather.data
import com.mintanables.LocoWeather.BuildConfig

object Constants {
    val TAG: String = "LocoWeather"
    val API_KEY : String = BuildConfig.WEATHER_API_KEY
    val BASE_URL : String = "https://api.openweathermap.org/forecast/${API_KEY}/"
    val BERLIN_LONGITUDE = "13.4050"
    val BERLIN_LATITUDE = "52.5200"
}
