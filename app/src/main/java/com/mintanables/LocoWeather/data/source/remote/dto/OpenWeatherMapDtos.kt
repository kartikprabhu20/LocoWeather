package com.mintanables.LocoWeather.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.mintanables.LocoWeather.domain.model.WeatherCondition

data class CurrentWeatherDto(
    @SerializedName("dt") var dt: Long? = null,
    @SerializedName("main") var main: MainDto? = null,
    @SerializedName("visibility") var visibility: Double? = null,
    @SerializedName("wind") var wind: WindDto? = null,
    @SerializedName("clouds") var clouds: CloudsDto? = null,
    @SerializedName("weather") var weather: List<WeatherCondition>? = null
)

data class ForecastDto(
    @SerializedName("list") var list: List<ForecastItemDto>? = null
)

data class ForecastItemDto(
    @SerializedName("dt") var dt: Long = 0,
    @SerializedName("main") var main: MainDto? = null,
    @SerializedName("weather") var weather: List<WeatherCondition>? = null,
    @SerializedName("clouds") var clouds: CloudsDto? = null,
    @SerializedName("wind") var wind: WindDto? = null,
    @SerializedName("visibility") var visibility: Double = 0.0,
    @SerializedName("pop") var pop: Double = 0.0,
    @SerializedName("dt_txt") var dtTxt: String = ""
)

data class MainDto(
    @SerializedName("temp") var temp: Double = 0.0,
    @SerializedName("feels_like") var feelsLike: Double = 0.0,
    @SerializedName("temp_min") var tempMin: Double = 0.0,
    @SerializedName("temp_max") var tempMax: Double = 0.0,
    @SerializedName("pressure") var pressure: Double = 0.0,
    @SerializedName("humidity") var humidity: Double = 0.0,
    @SerializedName("grnd_level") var grndLevel: Double = 0.0
)

data class WindDto(
    @SerializedName("speed") var speed: Double = 0.0,
    @SerializedName("deg") var deg: Double = 0.0,
    @SerializedName("gust") var gust: Double = 0.0
)

data class CloudsDto(
    @SerializedName("all") var all: Double = 0.0
)
