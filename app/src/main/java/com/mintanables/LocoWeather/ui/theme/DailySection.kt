package com.mintanables.LocoWeather.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mintanables.LocoWeather.domain.model.DailyItem
import com.mintanables.LocoWeather.presentation.WeatherViewModel
import com.mintanables.LocoWeather.ui.components.TitleCaptionText
import com.mintanables.LocoWeather.ui.components.TitleSubtitleText
import com.mintanables.LocoWeather.R
import com.mintanables.LocoWeather.domain.model.Temp

@Composable
fun DailySection(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = viewModel()
) {
    val dailyList by viewModel.dailyList.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.next_days),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        DailyList(dailyList)
    }
}

@Composable
private fun DailyList(
    weatherList: List<DailyItem>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) {
    LazyColumn(modifier = modifier, state = listState) {
        items(weatherList) { dailyItem ->
            Column(Modifier.fillMaxWidth()) {
                DailyListItem(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    item = dailyItem,
                )
                Divider(
                    color = MaterialTheme.colorScheme.outlineVariant,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.windowInsetsBottomHeight(WindowInsets.navigationBars))
        }
    }
}

@Composable
fun DailyListItem(item: DailyItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = item.iconId),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }
        Spacer(Modifier.width(16.dp))

        TitleCaptionText(
            title = item.date, 
            caption = item.summary.replaceFirstChar { it.uppercase() }, 
            modifier = Modifier.weight(1f)
        )

        Spacer(Modifier.width(16.dp))

        TitleSubtitleText(
            title = "${item.temperatureHigh} \u2109", 
            subtitle = "${item.temperatureLow} \u2109"
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun DailyItemPreview() {
    LocoWeatherTheme {
        DailyListItem(
            DailyItem(
                summary = "Clear Skies", 
                dt = 1111111, 
                temp = Temp(min = 40.0, max = 65.0),
                temperatureHigh = 65.0, 
                temperatureLow = 40.0, 
                iconId = R.drawable.clear_day,
                date = "28/04/2026"
            )
        )
    }
}
