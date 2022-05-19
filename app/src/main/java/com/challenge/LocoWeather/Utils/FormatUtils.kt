package com.challenge.LocoWeather.Utils

import android.util.Log
import com.challenge.LocoWeather.R
import com.challenge.LocoWeather.data.Constants.TAG
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
    when(icon){
        "clear-day"-> return  R.drawable.clear_day
        "cloudy"-> return  R.drawable.cloudy
        "clear-night"-> return  R.drawable.clear_night
        "fog"-> return  R.drawable.fog
        "partly-cloudy-day"-> return  R.drawable.partly_cloudy_day

        "partly-cloudy-night"-> return  R.drawable.partly_cloudy_night
        "rain-snow-showers-day"-> return  R.drawable.rain_snow
        "rain-snow-showers-night"-> return  R.drawable.rain_snow
        "rain-snow"-> return  R.drawable.rain_snow
        "rain"-> return  R.drawable.rain

        "showers-day"-> return  R.drawable.showers_day
        "showers-night"-> return  R.drawable.showers_night
        "sleet"-> return  R.drawable.sleet
        "snow-showers-day"-> return  R.drawable.snow_showers_day
        "snow-showers-night"-> return  R.drawable.snow_showers_night

        "snow"-> return  R.drawable.snow
        "thunder-rain"-> return  R.drawable.thunder_rain
        "hunder-showers-day"-> return  R.drawable.thunder_showers_day
        "thunder-showers-night"-> return  R.drawable.thunder_showers_night
        "thunder"-> return  R.drawable.thunder
        "wind"-> return  R.drawable.wind
    }
    return R.drawable.clear_day
}
