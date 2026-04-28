package com.mintanables.LocoWeather.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {
    val locationFlow: StateFlow<String>
    val unitFlow: StateFlow<String> // "imperial" or "metric"
    fun setLocation(location: String)
    fun setUnit(unit: String)
    fun getLocation(): String
    fun getUnit(): String
}
