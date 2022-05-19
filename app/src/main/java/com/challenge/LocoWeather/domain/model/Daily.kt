package com.challenge.LocoWeather.domain.model

import com.google.gson.annotations.SerializedName

data class Daily (

    @SerializedName("summary" ) var summary : String?         = null,
    @SerializedName("icon"    ) var icon    : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<DailyData> = arrayListOf()
)