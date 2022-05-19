package com.challenge.LocoWeather

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.challenge.LocoWeather.Utils.LocationUtils
import com.challenge.LocoWeather.data.Constants.BERLIN_LATITUDE
import com.challenge.LocoWeather.data.Constants.BERLIN_LONGITUDE
import com.challenge.LocoWeather.data.Constants.TAG
import com.challenge.LocoWeather.presentation.WeatherViewModel
import com.challenge.LocoWeather.ui.HomeScreen
import com.challenge.LocoWeather.ui.LandingScreen
import com.challenge.LocoWeather.ui.theme.LocoWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val PERMISSION_ID = 111

    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LocoWeatherTheme {
                MainScreen()
            }
        }
        getLocation()
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    private fun updateLocation(){
        LocationUtils.getLocation(this) { location ->
            Log.i(TAG, location.toString())
            viewModel.getWeatherInfoByLocation("${location?.latitude ?: BERLIN_LATITUDE},${location?.longitude ?: BERLIN_LONGITUDE}")
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (LocationUtils.isLocationPermissionsGranted(this)) {
            if (LocationUtils.isLocationEnabled(this)) {
                updateLocation()
            } else {
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Granted. Start getting the location information
                updateLocation()
            }
        }
    }
}


@Composable
fun MainScreen() {
    Surface(
        color = MaterialTheme.colors.primary
    ) {
        var showLandingScreen by rememberSaveable { mutableStateOf(true) }
        if (showLandingScreen) {
            LandingScreen(onTimeout = { showLandingScreen = false })
        } else {
            HomeScreen()
        }
    }
}

@Preview(device= Devices.PIXEL_4_XL)
@Composable
fun MainScreenPrevice() {
    LocoWeatherTheme {
        MainScreen()
    }
}