package com.challenge.LocoWeather.domain.model

data class HourlyData (
    var time: Long,
    var summary: String = "",
    var icon: String = "",
    var precipIntensity: Double,
    var precipProbability: Double,
    var temperature: Double,
    var apparentTemperature: Double,
    var dewPoint: Double,
    var humidity: Double,
    var pressure: Double,
    var windSpeed: Double,
    var windGust: Double,
    var windBearing: Double,
    var cloudCover: Double,
    var uvIndex: Double,
    var visibility: Double,
    var ozone: Double,
    var iconId: Int,
    var formatedTime: String
    )