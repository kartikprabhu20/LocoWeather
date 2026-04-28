package com.mintanables.LocoWeather

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mintanables.LocoWeather.Utils.LocationUtils
import com.mintanables.LocoWeather.data.Constants.BERLIN_LATITUDE
import com.mintanables.LocoWeather.data.Constants.BERLIN_LONGITUDE
import com.mintanables.LocoWeather.data.Constants.TAG
import com.mintanables.LocoWeather.presentation.WeatherViewModel
import com.mintanables.LocoWeather.ui.HomeScreen
import com.mintanables.LocoWeather.ui.LandingScreen
import com.mintanables.LocoWeather.ui.theme.LocoWeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val PERMISSION_ID = 111

    private val viewModel: WeatherViewModel by viewModels()
    @javax.inject.Inject
    lateinit var settingsRepository: com.mintanables.LocoWeather.domain.repository.SettingsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LocoWeatherTheme {
                MainAppNavHost(viewModel, settingsRepository)
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
        val customLoc = settingsRepository.getLocation().trim()
        if (customLoc.isNotEmpty()) {
            viewModel.getWeatherInfoByLocation(customLoc)
        } else {
            LocationUtils.getLocation(this) { location ->
                Log.i(TAG, location.toString())
                viewModel.getWeatherInfoByLocation("${location?.latitude ?: BERLIN_LATITUDE},${location?.longitude ?: BERLIN_LONGITUDE}")
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (LocationUtils.isLocationPermissionsGranted(this)) {
            if (LocationUtils.isLocationEnabled(this)) {
                updateLocation()
            } else {
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                updateLocation() // Proceed to check if custom location exists anyway
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            updateLocation() // Always try to update
        }
    }
}


@Composable
fun MainAppNavHost(
    viewModel: WeatherViewModel,
    settingsRepository: com.mintanables.LocoWeather.domain.repository.SettingsRepository
) {
    val navController = androidx.navigation.compose.rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                viewModel = viewModel, 
                onNavigateToSettings = { navController.navigate("settings") }
            )
        }
        composable("settings") {
            com.mintanables.LocoWeather.ui.SettingsScreen(
                settingsRepository = settingsRepository,
                onBack = { 
                    viewModel.getWeatherInfoByLocation("refresh") // RemoteSource handles resolution
                    navController.popBackStack() 
                }
            )
        }
    }
}

@Composable
fun MainScreen(viewModel: WeatherViewModel = hiltViewModel(), onNavigateToSettings: () -> Unit = {}) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = androidx.compose.material3.MaterialTheme.colorScheme.background
    ) {
        var showLandingScreen by rememberSaveable { mutableStateOf(true) }
        val isLoading by rememberSaveable { viewModel.isLoading }

        if (showLandingScreen && isLoading) {
            LandingScreen(onTimeout = { showLandingScreen = false })
        } else {
            HomeScreen(viewModel = viewModel, onNavigateToSettings = onNavigateToSettings)
        }
    }
}