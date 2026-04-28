package com.mintanables.LocoWeather.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mintanables.LocoWeather.domain.model.DailyItem
import com.mintanables.LocoWeather.presentation.WeatherViewModel
import com.mintanables.LocoWeather.ui.components.TitleCaptionText
import com.mintanables.LocoWeather.ui.components.TitleSubtitleText
import com.google.accompanist.insets.navigationBarsHeight
import com.mintanables.LocoWeather.R

@Composable
fun DailySection(modifier: Modifier = Modifier,
                 viewModel: WeatherViewModel = viewModel()
) {

    val dailyList by viewModel.dailyList.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {

        Text(
            text = stringResource(id = R.string.next_days),
            fontSize = 24.sp,
            modifier = Modifier.padding(4.dp),
            color = MaterialTheme.colors.primary
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
            Column(Modifier.fillParentMaxWidth()) {
                DailyListItem(
                    modifier = Modifier.fillParentMaxWidth(),
                    item = dailyItem,
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
fun DailyListItem(item: DailyItem,modifier: Modifier = Modifier) {

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

        TitleCaptionText(title = item.date, caption = item.summary.replaceFirstChar { it.uppercase() }, modifier.weight(1f))

        Spacer(Modifier.width(24.dp))

        TitleSubtitleText(title = "${item.temperatureHigh} \u2109" , subtitle =  "${item.temperatureLow} \u2109" )
    }
}

@Preview(device= Devices.PIXEL_4_XL)
@Composable
fun DailyItemPreview(){
    DailyListItem(DailyItem(summary = "summary", dt = 1111111, temperatureHigh = 10.0, temperatureLow = 1.0, iconId = 0))
}
