package com.challenge.LocoWeather.domain.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Weather(
    @SerializedName("latitude"  ) var latitude  : Double?    = null,
    @SerializedName("longitude" ) var longitude : Double?    = null,
    @SerializedName("timezone"  ) var timezone  : String?    = null,
    @SerializedName("currently" ) var currently : Currently? = Currently(),
    @SerializedName("hourly"    ) var hourly    : Hourly?    = Hourly(),
    @SerializedName("daily"     ) var daily     : Daily?     = Daily(),
    @SerializedName("flags"     ) var flags     : Flags?     = Flags(),
    @SerializedName("offset"    ) var offset    : Int?       = null
)
