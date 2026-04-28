package com.mintanables.LocoWeather.domain.model

import com.google.gson.annotations.SerializedName

data class Hourly (

    @SerializedName("summary" ) var summary : String?         = null,
    @SerializedName("icon"    ) var icon    : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<HourlyData> = arrayListOf()
)
