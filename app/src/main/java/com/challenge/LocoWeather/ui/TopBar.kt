//package com.challenge.LocoWeather.ui
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.launch
//
//
//@Composable
//fun TopBar(
//    scope: CoroutineScope,
//    scaffoldState: ScaffoldState
//) {
//    TopAppBar(
//        title = {
//            Row(
//                Modifier.fillMaxSize(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                ProvideTextStyle(value = MaterialTheme.typography.h6) {
//                    CompositionLocalProvider(
//                        LocalContentAlpha provides ContentAlpha.high,
//                    ) {
//                        Text(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(0.dp, 0.dp, 64.dp, 0.dp),
//                            textAlign = TextAlign.Center,
//                            maxLines = 1,
//                            text = stringResource(R.string.current_location_string),
//                            color = MaterialTheme.colors.primary
//                        )
//                    }
//                }
//            }
//        },
//        navigationIcon = {
//            IconButton(onClick = {
//                scope.launch {
//                    scaffoldState.drawerState.open()
//                }
//            }) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_menu),
//                    stringResource(R.string.description)
//                )
//            }
//        },
//        backgroundColor = Color(0, 0, 0, 0),
//        contentColor = Color.Black,
//        elevation = 0.dp,
//    )
//}
