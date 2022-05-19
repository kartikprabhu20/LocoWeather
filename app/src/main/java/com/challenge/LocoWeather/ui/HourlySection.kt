package com.challenge.LocoWeather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.challenge.LocoWeather.R
import com.challenge.LocoWeather.domain.model.HourlyData
import com.challenge.LocoWeather.presentation.WeatherViewModel
import com.challenge.LocoWeather.ui.components.TitleCaptionText
import com.challenge.LocoWeather.ui.components.TitleSubtitleText
import com.google.accompanist.insets.navigationBarsHeight


@Composable
fun HourlySection(modifier: Modifier = Modifier,
                 viewModel: WeatherViewModel = viewModel()
) {

    val hourlyList by viewModel.hourlyList.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {

        Text(
            text = stringResource(id = R.string.next_hours),
            fontSize = 24.sp,
            modifier = Modifier.padding(4.dp),
            color = MaterialTheme.colors.primary
        )

        HourlyList(hourlyList)

    }
}


@Composable
private fun HourlyList(
    weatherList: List<HourlyData>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) {
    LazyColumn(modifier = modifier, state = listState) {
        items(weatherList) { hourlyItem ->
            Column(Modifier.fillParentMaxWidth()) {
                HourlyItem(
                    modifier = Modifier.fillParentMaxWidth(),
                    item = hourlyItem,
                )
                Divider(
                    color = MaterialTheme.colors.primary
                )
            }
        }
        item {
            Spacer(modifier = Modifier.navigationBarsHeight())
        }
    }
}

@Composable
fun HourlyItem(item: HourlyData, modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .padding(top = 12.dp, bottom = 12.dp)
    ) {
        Box(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = item.iconId),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
            )
        }
        Spacer(Modifier.width(24.dp))

        TitleCaptionText(title = item.formatedTime, caption = item.summary, modifier.weight(1f))

        Spacer(Modifier.width(24.dp))

        TitleSubtitleText(title = "${item.temperature} \u2109" , subtitle =  "" )
    }
}
