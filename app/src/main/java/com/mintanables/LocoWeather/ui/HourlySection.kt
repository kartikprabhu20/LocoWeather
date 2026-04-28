package com.mintanables.LocoWeather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mintanables.LocoWeather.domain.model.HourlyItem
import com.mintanables.LocoWeather.presentation.WeatherViewModel
import com.mintanables.LocoWeather.ui.components.TitleCaptionText
import com.mintanables.LocoWeather.ui.components.TitleSubtitleText
import com.mintanables.LocoWeather.R
import androidx.compose.ui.tooling.preview.Preview
import com.mintanables.LocoWeather.ui.theme.LocoWeatherTheme
import com.mintanables.LocoWeather.domain.model.WeatherCondition

@Composable
fun HourlySection(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel = viewModel()
) {
    val hourlyList by viewModel.hourlyList.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.next_hours),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        HourlyList(hourlyList)
    }
}

@Composable
private fun HourlyList(
    weatherList: List<HourlyItem>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) {
    LazyColumn(modifier = modifier, state = listState) {
        items(weatherList) { hourlyItem ->
            Column(Modifier.fillMaxWidth()) {
                HourlyListItem(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    item = hourlyItem,
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
fun HourlyListItem(item: HourlyItem, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = item.iconId),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
            )
        }
        Spacer(Modifier.width(16.dp))

        TitleCaptionText(
            title = item.formatedTime, 
            caption = item.weather?.firstOrNull()?.description?.replaceFirstChar { it.uppercase() } ?: "", 
            modifier = Modifier.weight(1f)
        )

        Spacer(Modifier.width(16.dp))

        TitleSubtitleText(
            title = "${item.temp} ${item.unitSymbol}", 
            subtitle = "" 
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HourlyListItemPreview() {
    LocoWeatherTheme {
        HourlyListItem(
            item = HourlyItem(
                temp = 42.5,
                formatedTime = "14:00",
                iconId = R.drawable.clear_day,
                weather = listOf(WeatherCondition(description = "Clear sky"))
            )
        )
    }
}
