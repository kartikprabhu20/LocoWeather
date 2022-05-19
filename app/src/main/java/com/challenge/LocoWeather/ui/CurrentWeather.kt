package com.challenge.LocoWeather.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.challenge.LocoWeather.data.Constants
import com.challenge.LocoWeather.presentation.WeatherViewModel


@Composable
fun CurrentWeather(modifier: Modifier = Modifier,
                   viewModel: WeatherViewModel = viewModel()
) {

    val temperature by rememberSaveable {viewModel.currentTemperature}
    val summary by rememberSaveable {viewModel.currentSummary}
    val imageID by rememberSaveable {viewModel.currentImageIcon}

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(corner = CornerSize(24.dp)))) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "$temperature \u2109",
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 60.sp
                )
                Image(
                    painter = painterResource(imageID),
                    contentDescription = "icon placeholder",
                    modifier = Modifier
                        .size(160.dp)
                        .padding(4.dp))
                Text(
                    text = summary,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(4.dp),
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(8.dp))
                OtherDetails(modifier)
            }
        }

    }
}


@Composable
fun CurrentDate(viewModel: WeatherViewModel = hiltViewModel()) {
    val currentDate by remember{viewModel.currentDate}

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            shape = RoundedCornerShape(32.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = currentDate,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(8.dp),
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun OtherDetails(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = hiltViewModel()) {

    val pressure by remember {viewModel.currentPressure}
    val humidity by remember {viewModel.currentHumidity}
    val visibility by remember {viewModel.currentVisibility}
    val dewpoint by remember {viewModel.currentDewpoint}

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Details",
            color = MaterialTheme.colors.primary,
            fontSize = 24.sp,
            style = MaterialTheme.typography.h3
        )
        RowPairText("Humidity",humidity)
        RowPairText("Pressure",pressure)
        RowPairText("Visibility",visibility)
        RowPairText("Dew Point",dewpoint)
    }
}

@Composable
fun RowPairText(title: String, value: String) {
    Row(){
        Text(
            text = title,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp
        )
        Text(
            text = value,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp
        )
    }
}

@Preview(device= Devices.PIXEL_4_XL)
@Composable
fun RowPairTextPreview() {
    RowPairText("test","test")
}