package com.mintanables.LocoWeather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.mintanables.LocoWeather.ui.theme.LocoWeatherTheme
import kotlinx.coroutines.delay
import com.mintanables.LocoWeather.R

private const val SplashWaitTime: Long = 10000

@Composable
fun LandingScreen(modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        val currentOnTimeout by rememberUpdatedState(onTimeout)
        LaunchedEffect(true) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }
        Image(painterResource(id = R.drawable.cloud), contentDescription = null,
        modifier = Modifier.clip(CircleShape)                       // clip to the circle shape
        )
    }
}

@Preview(device= Devices.PIXEL_4_XL)
@Composable
fun LandingScreenPrevice() {
    LocoWeatherTheme {
        LandingScreen(onTimeout = {})
    }
}
