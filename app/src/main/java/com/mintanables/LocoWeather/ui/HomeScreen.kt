package com.mintanables.LocoWeather.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mintanables.LocoWeather.presentation.WeatherViewModel
import com.mintanables.LocoWeather.ui.theme.DailySection
import androidx.compose.ui.tooling.preview.Preview
import com.mintanables.LocoWeather.ui.theme.LocoWeatherTheme

enum class WeatherTab {
    Daily, Hourly
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel(),
    onNavigateToSettings: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.systemBarsPadding(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        HomeContent(
            modifier = Modifier.padding(innerPadding),
            viewModel = viewModel,
            onNavigateToSettings = onNavigateToSettings
        )
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel,
    onNavigateToSettings: () -> Unit = {}
) {
    var tabSelected by remember { mutableStateOf(WeatherTab.Daily) }

    BackdropScaffold(
        modifier = modifier,
        scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
        frontLayerScrimColor = Color.Unspecified,
        backLayerBackgroundColor = MaterialTheme.colorScheme.background,
        frontLayerBackgroundColor = MaterialTheme.colorScheme.surface,
        appBar = {
            HomeTabBar(
                tabSelected = tabSelected, 
                onTabSelected = { tabSelected = it },
                onNavigateToSettings = onNavigateToSettings
            )
        },
        backLayerContent = {
            CurrentWeatherSection(viewModel = viewModel)
        },
        frontLayerContent = {
            when (tabSelected) {
                WeatherTab.Daily -> {
                    DailySection()
                }
                WeatherTab.Hourly ->{
                    HourlySection()
                }
            }
        }
    )
}

@Composable
fun CurrentWeatherSection(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel
) {
    val temperature by remember { viewModel.currentTemperature }
    val summary by remember { viewModel.currentSummary }
    val imageID by remember { viewModel.currentImageIcon }
    val pressure by remember { viewModel.currentPressure }
    val humidity by remember { viewModel.currentHumidity }
    val visibility by remember { viewModel.currentVisibility }
    val dewpoint by remember { viewModel.currentDewpoint }
    val currentDate by remember { viewModel.currentDate }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        CurrentDate(date = currentDate)
        CurrentWeather(
            temperature = temperature,
            summary = summary,
            imageID = imageID,
            pressure = pressure,
            humidity = humidity,
            visibility = visibility,
            dewpoint = dewpoint
        )
    }
}

// ------ PREVIEWS ------

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LocoWeatherTheme {
        HomeScreen()
    }
}
