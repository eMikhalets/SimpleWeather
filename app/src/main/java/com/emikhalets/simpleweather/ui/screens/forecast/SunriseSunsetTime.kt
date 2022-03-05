package com.emikhalets.simpleweather.ui.screens.forecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emikhalets.simpleweather.ui.screens.base.TextPrimary

@Composable
fun SunriseSunsetTime(
    sunriseTime: String,
    sunsetTime: String,
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.padding(16.dp, 40.dp)) {
        TextPrimary(text = "Not implemented yet", fontSize = 24.sp)
    }
}