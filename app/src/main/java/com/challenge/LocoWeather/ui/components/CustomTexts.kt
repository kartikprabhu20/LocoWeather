package com.challenge.LocoWeather.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun TitleCaptionText(title: String, caption: String, modifier: Modifier = Modifier) {
    Column (modifier = modifier){
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = caption,
            style = MaterialTheme.typography.caption,
        )
    }
}

@Composable
fun TitleSubtitleText(title: String, subtitle: String, modifier: Modifier = Modifier){

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = title ,
            style = MaterialTheme.typography.h6
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.caption
        )
    }
}