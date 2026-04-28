package com.mintanables.LocoWeather.Utils

import android.util.Log
import com.mintanables.LocoWeather.R
import com.mintanables.LocoWeather.data.Constants.TAG
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


fun getFormattedDate(time: Long?): String {
    if(time==null)
        return "Unknown"

    Log.d(TAG, (time*1000).toString())
    val date = Date(time*1000)
    return DateFormat.getDateInstance(DateFormat.FULL).format(date)
}

fun getFormattedDateOnly(time: Long?): String {
    if(time==null)
        return "Unknown"
    Log.d(TAG, (time*1000).toString())

    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val date: String = sdf.format(time*1000)
    return date
}

fun getFormattedTime(time: Long): String {

    val sdf = SimpleDateFormat("HH:mm")
    val formatedTime = sdf.format(time*1000)

    return formatedTime
}

fun formatIcons(icon: String?): Int {
    if (icon == null) return R.drawable.clear_day
    
    // OpenWeatherMap icons
    // 01d: clear sky day, 01n: clear sky night
    // 02d: few clouds day, 02n: few clouds night
    // 03d, 03n: scattered clouds
    // 04d, 04n: broken clouds
    // 09d, 09n: shower rain
    // 10d, 10n: rain
    // 11d, 11n: thunderstorm
    // 13d, 13n: snow
    // 50d, 50n: mist
    
    return when(icon) {
        "01d" -> R.drawable.clear_day
        "01n" -> R.drawable.clear_night
        "02d" -> R.drawable.partly_cloudy_day
        "02n" -> R.drawable.partly_cloudy_night
        "03d", "03n" -> R.drawable.cloudy
        "04d", "04n" -> R.drawable.cloudy
        "09d" -> R.drawable.showers_day
        "09n" -> R.drawable.showers_night
        "10d" -> R.drawable.rain
        "10n" -> R.drawable.rain
        "11d", "11n" -> R.drawable.thunder_rain
        "13d" -> R.drawable.snow_showers_day
        "13n" -> R.drawable.snow_showers_night
        "50d", "50n" -> R.drawable.fog
        else -> R.drawable.clear_day
    }
}
