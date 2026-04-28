package com.mintanables.LocoWeather.data.repository

import android.content.SharedPreferences
import com.mintanables.LocoWeather.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SettingsRepository {

    private val KEY_LOCATION = "PREF_LOCATION"
    private val KEY_UNIT = "PREF_UNIT"

    private val _locationFlow = MutableStateFlow(sharedPreferences.getString(KEY_LOCATION, "") ?: "")
    override val locationFlow: StateFlow<String> = _locationFlow

    private val _unitFlow = MutableStateFlow(sharedPreferences.getString(KEY_UNIT, "imperial") ?: "imperial")
    override val unitFlow: StateFlow<String> = _unitFlow

    override fun setLocation(location: String) {
        sharedPreferences.edit().putString(KEY_LOCATION, location).apply()
        _locationFlow.value = location
    }

    override fun setUnit(unit: String) {
        sharedPreferences.edit().putString(KEY_UNIT, unit).apply()
        _unitFlow.value = unit
    }

    override fun getLocation(): String {
        return sharedPreferences.getString(KEY_LOCATION, "") ?: ""
    }

    override fun getUnit(): String {
        return sharedPreferences.getString(KEY_UNIT, "imperial") ?: "imperial"
    }
}
