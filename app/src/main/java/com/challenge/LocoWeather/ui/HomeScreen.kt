package com.challenge.LocoWeather.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.challenge.LocoWeather.presentation.WeatherViewModel
import com.google.accompanist.insets.statusBarsPadding
import androidx.lifecycle.viewmodel.compose.viewModel
import com.challenge.LocoWeather.ui.theme.DailySection


enum class HomeScreen {
    Daily, Hourly
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.statusBarsPadding(),
    ) {
        HomeContent()
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
        appBar = {
            HomeTabBar(tabSelected, onTabSelected = { tabSelected = it })
        },
        backLayerContent = {

            CurrentWeatherSection()
        },
        frontLayerContent = {

            when (tabSelected) {
                HomeScreen.Daily -> {
                    DailySection(
                        modifier
                    )
                }
                HomeScreen.Hourly->{
                    HourlySection(
                        modifier
                    )
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        CurrentDate(viewModel)
//        Spacer(modifier = Modifier.height(8.dp))
        CurrentWeather()
    }

}



