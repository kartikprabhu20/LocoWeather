package com.mintanables.LocoWeather.domain.model

import com.mintanables.LocoWeather.R
import com.google.gson.annotations.SerializedName


data class DailyData (
    @SerializedName("time"                        ) var time                        : Long?    = null,
    @SerializedName("summary"                     ) var summary                     : String? = null,
    @SerializedName("icon"                        ) var icon                        : String? = null,
    @SerializedName("sunriseTime"                 ) var sunriseTime                 : Int?    = null,
    @SerializedName("sunsetTime"                  ) var sunsetTime                  : Int?    = null,
    @SerializedName("moonPhase"                   ) var moonPhase                   : Double? = null,
    @SerializedName("precipIntensity"             ) var precipIntensity             : Double? = null,
    @SerializedName("precipIntensityMax"          ) var precipIntensityMax          : Double? = null,
    @SerializedName("precipIntensityMaxTime"      ) var precipIntensityMaxTime      : Int?    = null,
    @SerializedName("precipProbability"           ) var precipProbability           : Double? = null,
    @SerializedName("precipType"                  ) var precipType                  : String? = null,
    @SerializedName("temperatureHigh"             ) var temperatureHigh             : Double? = null,
    @SerializedName("temperatureHighTime"         ) var temperatureHighTime         : Int?    = null,
    @SerializedName("temperatureLow"              ) var temperatureLow              : Double? = null,
    @SerializedName("temperatureLowTime"          ) var temperatureLowTime          : Int?    = null,
    @SerializedName("apparentTemperatureHigh"     ) var apparentTemperatureHigh     : Double? = null,
    @SerializedName("apparentTemperatureHighTime" ) var apparentTemperatureHighTime : Int?    = null,
    @SerializedName("apparentTemperatureLow"      ) var apparentTemperatureLow      : Double? = null,
    @SerializedName("apparentTemperatureLowTime"  ) var apparentTemperatureLowTime  : Int?    = null,
    @SerializedName("dewPoint"                    ) var dewPoint                    : Double? = null,
    @SerializedName("humidity"                    ) var humidity                    : Double? = null,
    @SerializedName("pressure"                    ) var pressure                    : Double? = null,
    @SerializedName("windSpeed"                   ) var windSpeed                   : Double? = null,
    @SerializedName("windGust"                    ) var windGust                    : Double? = null,
    @SerializedName("windGustTime"                ) var windGustTime                : Int?    = null,
    @SerializedName("windBearing"                 ) var windBearing                 : Int?    = null,
    @SerializedName("cloudCover"                  ) var cloudCover                  : Double? = null,
    @SerializedName("uvIndex"                     ) var uvIndex                     : Int?    = null,
    @SerializedName("uvIndexTime"                 ) var uvIndexTime                 : Int?    = null,
    @SerializedName("visibility"                  ) var visibility                  : Double?    = null,
    @SerializedName("ozone"                       ) var ozone                       : Double? = null,
    @SerializedName("temperatureMin"              ) var temperatureMin              : Double? = null,
    @SerializedName("temperatureMinTime"          ) var temperatureMinTime          : Int?    = null,
    @SerializedName("temperatureMax"              ) var temperatureMax              : Double? = null,
    @SerializedName("temperatureMaxTime"          ) var temperatureMaxTime          : Int?    = null,
    @SerializedName("apparentTemperatureMin"      ) var apparentTemperatureMin      : Double? = null,
    @SerializedName("apparentTemperatureMinTime"  ) var apparentTemperatureMinTime  : Int?    = null,
    @SerializedName("apparentTemperatureMax"      ) var apparentTemperatureMax      : Double? = null,
    @SerializedName("apparentTemperatureMaxTime"  ) var apparentTemperatureMaxTime  : Int?    = null,
    var date                        : String    = "",
    var iconId: Int = R.drawable.clear_day
    )