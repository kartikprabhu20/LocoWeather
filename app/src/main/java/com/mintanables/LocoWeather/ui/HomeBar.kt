package com.mintanables.LocoWeather.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mintanables.LocoWeather.R
import androidx.compose.ui.tooling.preview.Preview
import com.mintanables.LocoWeather.ui.theme.LocoWeatherTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTabBar(
    tabSelected: HomeScreen,
    onTabSelected: (HomeScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        TopAppBar(
            title = { 
                Text(
                    text = stringResource(R.string.app_name), 
                    style = MaterialTheme.typography.titleLarge
                ) 
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )

        TabBar(modifier = modifier) { tabBarModifier ->
            Tabs(
                modifier = tabBarModifier,
                titles = HomeScreen.values().map { it.name },
                tabSelected = tabSelected,
                onTabSelected = { newTab -> onTabSelected(HomeScreen.values()[newTab.ordinal]) }
            )
        }
    }
}

@Composable
fun Tabs(
    modifier: Modifier,
    titles: List<String>,
    tabSelected: HomeScreen,
    onTabSelected: (HomeScreen) -> Unit
) {
    TabRow(
        selectedTabIndex = tabSelected.ordinal,
        modifier = modifier.padding(bottom = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary,
        indicator = { },
        divider = { }
    ) {
        titles.forEachIndexed { index, title ->
            val selected = index == tabSelected.ordinal

            var textModifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            if (selected) {
                textModifier =
                    Modifier
                        .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary), RoundedCornerShape(16.dp))
                        .then(textModifier)
            }

            Tab(
                selected = selected,
                onClick = { onTabSelected(HomeScreen.values()[index]) },
                modifier = textModifier.clip(RoundedCornerShape(32.dp)),
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = if (selected) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal
                )
            }
        }
    }
}


@Composable
fun TabBar(
    modifier: Modifier = Modifier,
    children: @Composable (Modifier) -> Unit
) {
    Row(modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            children(
                Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTabBarPreview() {
    LocoWeatherTheme {
        HomeTabBar(
            tabSelected = HomeScreen.Daily,
            onTabSelected = {}
        )
    }
}
