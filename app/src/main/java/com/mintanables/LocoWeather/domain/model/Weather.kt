package com.mintanables.LocoWeather.domain.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("lat") var lat: Double? = null,
    @SerializedName("lon") var lon: Double? = null,
    @SerializedName("timezone") var timezone: String? = null,
    @SerializedName("timezone_offset") var timezoneOffset: Int? = null,
    @SerializedName("current") var current: Current? = null,
    @SerializedName("hourly") var hourly: List<HourlyItem>? = null,
    @SerializedName("daily") var daily: List<DailyItem>? = null
)

data class Current(
    @SerializedName("dt") var dt: Long? = null,
    @SerializedName("temp") var temp: Double? = null,
    @SerializedName("feels_like") var feelsLike: Double? = null,
    @SerializedName("pressure") var pressure: Double? = null,
    @SerializedName("humidity") var humidity: Double? = null,
    @SerializedName("dew_point") var dewPoint: Double? = null,
    @SerializedName("uvi") var uvi: Double? = null,
    @SerializedName("clouds") var clouds: Double? = null,
    @SerializedName("visibility") var visibility: Double? = null,
    @SerializedName("wind_speed") var windSpeed: Double? = null,
    @SerializedName("wind_deg") var windDeg: Double? = null,
    @SerializedName("weather") var weather: List<WeatherCondition>? = null
)

data class WeatherCondition(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("main") var main: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("icon") var icon: String? = null
)

data class HourlyItem(
    @SerializedName("dt") var dt: Long = 0,
    @SerializedName("temp") var temp: Double = 0.0,
    @SerializedName("feels_like") var feelsLike: Double = 0.0,
    @SerializedName("pressure") var pressure: Double = 0.0,
    @SerializedName("humidity") var humidity: Double = 0.0,
    @SerializedName("dew_point") var dewPoint: Double = 0.0,
    @SerializedName("uvi") var uvi: Double = 0.0,
    @SerializedName("clouds") var clouds: Double = 0.0,
    @SerializedName("visibility") var visibility: Double = 0.0,
    @SerializedName("wind_speed") var windSpeed: Double = 0.0,
    @SerializedName("wind_deg") var windDeg: Double = 0.0,
    @SerializedName("pop") var pop: Double = 0.0,
    @SerializedName("weather") var weather: List<WeatherCondition>? = null,
    // Add these for the UI to map internally like before
    var formatedTime: String = "", 
    var iconId: Int = 0
)

data class DailyItem(
    @SerializedName("dt") var dt: Long = 0,
    @SerializedName("sunrise") var sunrise: Long = 0,
    @SerializedName("sunset") var sunset: Long = 0,
    @SerializedName("summary") var summary: String = "",
    @SerializedName("temp") var temp: Temp = Temp(),
    @SerializedName("feels_like") var feelsLike: FeelsLike = FeelsLike(),
    @SerializedName("pressure") var pressure: Double = 0.0,
    @SerializedName("humidity") var humidity: Double = 0.0,
    @SerializedName("dew_point") var dewPoint: Double = 0.0,
    @SerializedName("wind_speed") var windSpeed: Double = 0.0,
    @SerializedName("wind_deg") var windDeg: Double = 0.0,
    @SerializedName("weather") var weather: List<WeatherCondition>? = null,
    @SerializedName("clouds") var clouds: Double = 0.0,
    @SerializedName("pop") var pop: Double = 0.0,
    @SerializedName("uvi") var uvi: Double = 0.0,
    // For UI
    var temperatureLow: Double = 0.0,
    var temperatureHigh: Double = 0.0,
    var date: String = "",
    var iconId: Int = 0
)

data class Temp(
    @SerializedName("day") var day: Double = 0.0,
    @SerializedName("min") var min: Double = 0.0,
    @SerializedName("max") var max: Double = 0.0,
    @SerializedName("night") var night: Double = 0.0,
    @SerializedName("eve") var eve: Double = 0.0,
    @SerializedName("morn") var morn: Double = 0.0
)

data class FeelsLike(
    @SerializedName("day") var day: Double = 0.0,
    @SerializedName("night") var night: Double = 0.0,
    @SerializedName("eve") var eve: Double = 0.0,
    @SerializedName("morn") var morn: Double = 0.0
)
