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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mintanables.LocoWeather.presentation.WeatherViewModel
import com.mintanables.LocoWeather.ui.theme.DailySection
import androidx.compose.ui.tooling.preview.Preview
import com.mintanables.LocoWeather.ui.theme.LocoWeatherTheme

enum class HomeScreen {
    Daily, Hourly
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.systemBarsPadding(),
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        HomeContent(Modifier.padding(innerPadding))
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
) {
    var tabSelected by remember { mutableStateOf(HomeScreen.Daily) }

    BackdropScaffold(
        modifier = modifier,
        scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
        frontLayerScrimColor = Color.Unspecified,
        backLayerBackgroundColor = MaterialTheme.colorScheme.background,
        frontLayerBackgroundColor = MaterialTheme.colorScheme.surface,
        appBar = {
            HomeTabBar(tabSelected, onTabSelected = { tabSelected = it })
        },
        backLayerContent = {
            CurrentWeatherSection()
        },
        frontLayerContent = {
            when (tabSelected) {
                HomeScreen.Daily -> {
                    DailySection(modifier)
                }
                HomeScreen.Hourly ->{
                    HourlySection(modifier)
                }
            }
        }
    )
}

@Composable
fun CurrentWeatherSection(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = viewModel()
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
