package com.mintanables.LocoWeather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mintanables.LocoWeather.presentation.WeatherViewModel
import com.mintanables.LocoWeather.R
import com.mintanables.LocoWeather.ui.theme.LocoWeatherTheme

@Composable
fun CurrentWeather(
    modifier: Modifier = Modifier,
    temperature: String = "--",
    summary: String = "--",
    imageID: Int = R.drawable.clear_day,
    pressure: String = "--",
    humidity: String = "--",
    visibility: String = "--",
    dewpoint: String = "--"
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(corner = CornerSize(24.dp))),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$temperature",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
                style = MaterialTheme.typography.headlineLarge
            )
            Image(
                painter = painterResource(imageID),
                contentDescription = "icon placeholder",
                modifier = Modifier
                    .size(140.dp)
                    .padding(4.dp)
            )
            Text(
                text = summary,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
            
            Divider(color = MaterialTheme.colorScheme.outlineVariant, modifier = Modifier.padding(horizontal = 24.dp))

            OtherDetails(
                pressure = pressure, 
                humidity = humidity, 
                visibility = visibility, 
                dewpoint = dewpoint,
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}


@Composable
fun CurrentDate(date: String = "Monday, Jan 1") {
    Box(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            shape = RoundedCornerShape(32.dp),
            color = MaterialTheme.colorScheme.tertiaryContainer
        ) {
            Text(
                text = date,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun OtherDetails(
    pressure: String,
    humidity: String,
    visibility: String,
    dewpoint: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Details",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        // Ensure proper alignment
        Column(
            modifier = Modifier.fillMaxWidth(0.8f), // Limit width to keep text centered but nicely aligned
        ) {
            RowPairText("Humidity", humidity)
            Spacer(modifier = Modifier.height(8.dp))
            RowPairText("Pressure", pressure)
            Spacer(modifier = Modifier.height(8.dp))
            RowPairText("Visibility", visibility)
            Spacer(modifier = Modifier.height(8.dp))
            RowPairText("Dew Point", dewpoint)
        }
    }
}

@Composable
fun RowPairText(title: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            textAlign = TextAlign.End
        )
    }
}

// ------ PREVIEWS ------

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun CurrentWeatherPreview() {
    LocoWeatherTheme {
        CurrentWeather(
            temperature = "72.4",
            summary = "Clear Sky",
            pressure = "1012.0",
            humidity = "45",
            visibility = "10000",
            dewpoint = "55.0"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentDatePreview() {
    LocoWeatherTheme {
        CurrentDate("Thursday, October 12")
    }
}

@Preview(showBackground = true)
@Composable
fun RowPairTextPreview() {
    LocoWeatherTheme {
        RowPairText("Humidity", "76%")
    }
}