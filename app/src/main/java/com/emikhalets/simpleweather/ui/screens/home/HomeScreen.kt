package com.emikhalets.simpleweather.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.ui.theme.*
import com.emikhalets.simpleweather.utils.previewHomeScreenHourlyForecast

@Composable
fun HomeScreen() {
    HomeScreen(
        cityName = "",
        date = "",
        iconUrl = "",
        temperature = 0,
        windSpeed = 0,
        humidity = 0,
        hourlyForecast = emptyList(),
    )
}

@Composable
fun HomeScreen(
    cityName: String,
    date: String,
    iconUrl: String,
    temperature: Int,
    windSpeed: Int,
    humidity: Int,
    hourlyForecast: List<HomeScreenHourlyEntity>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0C0C40),
                        Color(0xFF060620)
                    )
                )
            )

    ) {
        HomeScreenHeader(
            cityName = cityName,
            date = date,
        )
        HomeScreenGeneralInfo(
            iconUrl = iconUrl,
            temperature = temperature,
            windSpeed = windSpeed,
            humidity = humidity
        )
        HomeScreenHourlyForecast(
            hourlyForecast = hourlyForecast
        )
    }
}

@Composable
fun HomeScreenHeader(
    cityName: String,
    date: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(52.dp))
        Text(
            text = cityName,
            color = MaterialTheme.colors.primaryText,
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = date,
            color = MaterialTheme.colors.secondary,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(32.dp))
        HomeScreenHeaderSwitcher()
    }
}

@Composable
fun HomeScreenHeaderSwitcher() {
    var activeFirst by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier
            .background(
                color = MaterialTheme.colors.inactiveBackground,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
    ) {
        Text(
            text = stringResource(id = R.string.home_forecast),
            fontSize = 16.sp,
            color = activeTextColor(activeFirst),
            modifier = Modifier
                .activeBackground(activeFirst)
                .padding(40.dp, 16.dp)
                .clickable { activeFirst = true }
        )
        Text(
            text = stringResource(id = R.string.home_air_quality),
            fontSize = 16.sp,
            color = activeTextColor(!activeFirst),
            modifier = Modifier
                .activeBackground(!activeFirst)
                .padding(40.dp, 16.dp)
                .clickable { activeFirst = false }
        )
    }
}

private fun Modifier.activeBackground(active: Boolean): Modifier = composed {
    if (active) {
        background(
            color = MaterialTheme.colors.activeBackground,
            shape = RoundedCornerShape(12.dp)
        )
    } else {
        background(color = Color.Transparent)
    }
}

@Composable
private fun activeTextColor(active: Boolean): Color {
    return if (active) {
        MaterialTheme.colors.primaryText
    } else {
        MaterialTheme.colors.secondaryText
    }
}

@Composable
fun HomeScreenGeneralInfo(
    iconUrl: String,
    temperature: Int,
    windSpeed: Int,
    humidity: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(iconUrl)
                .crossfade(true)
                .placeholder(R.drawable.app_icon)
                .build(),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            HomeScreenGeneralInfoValueBlock(
                title = stringResource(id = R.string.home_temperature),
                value = stringResource(id = R.string.home_temperature_value, temperature)
            )
            HomeScreenGeneralInfoValueBlock(
                title = stringResource(id = R.string.home_wind_speed),
                value = stringResource(id = R.string.home_wind_speed_value, windSpeed)
            )
            HomeScreenGeneralInfoValueBlock(
                title = stringResource(id = R.string.home_humidity),
                value = stringResource(id = R.string.home_humidity_value, humidity)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun HomeScreenGeneralInfoValueBlock(
    title: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        Text(
            text = title,
            color = MaterialTheme.colors.secondaryText,
            fontSize = 16.sp
        )
        Text(
            text = value,
            color = MaterialTheme.colors.primaryText,
            fontSize = 20.sp
        )
    }
}

@Composable
fun HomeScreenHourlyForecast(
    hourlyForecast: List<HomeScreenHourlyEntity>
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(hourlyForecast) { entity ->
            HomeScreenHourlyBlock(
                iconUrl = entity.iconUrl,
                time = entity.time,
                temperature = entity.temperature,
            )
        }
    }
}

@Composable
fun HomeScreenHourlyBlock(
    iconUrl: String,
    time: String,
    temperature: Int,
) {
    val tempUnitStyle = SpanStyle(
        baselineShift = BaselineShift.Superscript,
        fontSize = 14.sp
    )

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(
                color = MaterialTheme.colors.activeBackground,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(20.dp, 16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(iconUrl)
                .crossfade(true)
                .placeholder(R.drawable.app_icon)
                .build(),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = time,
                color = MaterialTheme.colors.primaryText,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = buildAnnotatedString {
                    append(temperature.toString())
                    withStyle(tempUnitStyle) {
                        append(stringResource(id = R.string.home_celsius))
                    }
                },
                color = MaterialTheme.colors.primaryText,
                fontSize = 26.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(
            cityName = "San Fransisco",
            date = "May 28, 2021",
            iconUrl = "",
            temperature = 28,
            windSpeed = 10,
            humidity = 75,
            hourlyForecast = previewHomeScreenHourlyForecast(),
        )
    }
}