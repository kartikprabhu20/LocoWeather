package com.challenge.LocoWeather.domain.model

import com.google.gson.annotations.SerializedName

data class Flags (

    @SerializedName("sources"            ) var sources            : ArrayList<String> = arrayListOf(),
    @SerializedName("meteoalarm-license" ) var meteoalarm_license : String?           = null,
    @SerializedName("nearest-station"    ) var nearest_station    : Double?           = null,
    @SerializedName("units"              ) var units              : String?           = null

)
