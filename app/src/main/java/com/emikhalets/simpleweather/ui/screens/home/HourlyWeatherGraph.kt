package com.emikhalets.simpleweather.ui.screens.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.ui.screens.base.AppIcon
import com.emikhalets.simpleweather.ui.screens.base.TextPrimary
import com.emikhalets.simpleweather.ui.screens.base.TextSecondary
import com.emikhalets.simpleweather.ui.screens.base.WeatherUnits
import com.emikhalets.simpleweather.ui.theme.AppTheme

@Composable
fun HourlyWeatherGraph(hourlyForecastList: List<HourlyForecast>) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.padding(16.dp, 40.dp)) {
        TextPrimary(text = "Not implemented yet", fontSize = 24.sp)
    }
}

@Composable
private fun HourlyWeatherElement(
    maxTemperature: Int,
    minTemperature: Int,
    time: String,
    iconUrl: String,
    startLine: Float,
    endLine: Float,
    units: WeatherUnits,
) {
    val maxTemperatureText = stringResource(
        R.string.app_value_no_space, maxTemperature, stringResource(units.temperature())
    )
    val minTemperatureText = stringResource(
        R.string.app_value_no_space, minTemperature, stringResource(units.temperature())
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppIcon(imageUrl = iconUrl, size = 24.dp)
        Spacer(modifier = Modifier.height(8.dp))
        TextPrimary(text = maxTemperatureText, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(8.dp))
        TextSecondary(text = minTemperatureText, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Canvas(modifier = Modifier.size(60.dp)) {
            drawLine(
                start = Offset(0f, startLine),
                end = Offset(200f, endLine),
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextSecondary(text = time)
    }
}

@Preview(showBackground = true)
@Composable
private fun HourlyWeatherElementPreview() {
    AppTheme {
        HourlyWeatherElement(
            maxTemperature = 26,
            minTemperature = 18,
            time = "4 PM",
            iconUrl = "",
            startLine = 50f,
            endLine = 30f,
            units = WeatherUnits(),
        )
    }
}