package com.mintanables.LocoWeather.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mintanables.LocoWeather.R


@Composable
fun HomeTabBar(
    tabSelected: HomeScreen,
    onTabSelected: (HomeScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        TopAppBar(
            title = { Text(stringResource(R.string.app_name), fontSize = 18.sp) },
            backgroundColor = MaterialTheme.colors.primary,
        )

        TabBar(
            modifier = modifier
        ) { tabBarModifier ->
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
        modifier = modifier,
        contentColor = MaterialTheme.colors.onSurface,
        indicator = { },
        divider = { }
    ) {
        titles.forEachIndexed { index, title ->
            val selected = index == tabSelected.ordinal

            var textModifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            if (selected) {
                textModifier =
                    Modifier
                        .border(BorderStroke(2.dp, Color.White), RoundedCornerShape(16.dp))
                        .then(textModifier)
            }

            Tab(
                selected = selected,
                onClick = { onTabSelected(HomeScreen.values()[index]) },
                modifier = textModifier
            ) {
                Text(
                    text = title,
//                    color = MaterialTheme.colors.onPrimary
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
    Row(modifier) {
        Row(
//            Modifier.padding(top = 8.dp)
        ) {
            children(
                Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}
