package com.emikhalets.simpleweather.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.emikhalets.simpleweather.ui.screens.base.entity.DailyForecastEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.HourlyForecastEntity
import com.emikhalets.simpleweather.ui.theme.AppTheme
import com.emikhalets.simpleweather.ui.theme.inactiveBackground
import com.emikhalets.simpleweather.utils.activeBackground
import com.emikhalets.simpleweather.utils.activeTextColor
import com.emikhalets.simpleweather.utils.appSurface
import com.emikhalets.simpleweather.utils.previewHomeScreenDailyForecast
import com.emikhalets.simpleweather.utils.previewHomeScreenHourlyForecast

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state = viewModel.state

    LaunchedEffect("") {
        viewModel.getWeather()
    }

    state.current ?: return

    HomeScreen(
        cityName = state.current.location,
        date = state.current.date,
        iconUrl = state.current.iconUrl,
        temperature = state.current.temperature,
        windSpeed = state.current.windSpeed,
        humidity = state.current.humidity,
        dailyForecast = state.daily,
        hourlyForecast = state.hourly,
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
    dailyForecast: List<DailyForecastEntity>,
    hourlyForecast: List<HourlyForecastEntity>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .appSurface()
            .verticalScroll(rememberScrollState())
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
        HomeScreenForecast(
            list = dailyForecast,
            primaryText = stringResource(id = R.string.home_forecast_daily),
            secondaryText = {
                Text(
                    text = stringResource(id = R.string.home_view_full),
                    color = Color.Blue
                )
            },
            itemContent = { entity ->
                HomeScreenForecastDaily(
                    iconUrl = entity.iconUrl,
                    date = entity.date,
                    temperature = entity.temperature,
                    isActive = false,
                )
            }
        )
        HomeScreenForecast(
            list = hourlyForecast,
            primaryText = stringResource(id = R.string.home_forecast_hourly),
            secondaryText = {
                Text(
                    text = stringResource(id = R.string.home_view_full),
                    color = Color.Blue
                )
            },
            itemContent = { entity ->
                HomeScreenForecastHourly(
                    iconUrl = entity.iconUrl,
                    time = entity.time,
                    temperature = entity.temperature,
                    isActive = false,
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
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
            color = MaterialTheme.colors.primary,
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

@Composable
fun HomeScreenGeneralInfo(
    iconUrl: String,
    temperature: Int,
    windSpeed: Int,
    humidity: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https:$iconUrl")
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
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
            color = MaterialTheme.colors.secondary,
            fontSize = 16.sp
        )
        Text(
            text = value,
            color = MaterialTheme.colors.primary,
            fontSize = 20.sp
        )
    }
}

@Composable
fun <T> HomeScreenForecast(
    list: List<T>,
    primaryText: String,
    secondaryText: @Composable () -> Unit,
    itemContent: @Composable (item: T) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 8.dp)
        ) {
            Text(
                text = primaryText,
                fontSize = 24.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            secondaryText()
        }
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(list) { entity -> itemContent(entity) }
    }
}

@Composable
fun HomeScreenForecastHourly(
    iconUrl: String,
    time: String,
    temperature: Int,
    isActive: Boolean = true,
) {
    val unitStyle = SpanStyle(
        baselineShift = BaselineShift.Superscript,
        fontSize = 14.sp
    )

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(
                color = MaterialTheme.colors.inactiveBackground,
                shape = MaterialTheme.shapes.medium
            )
            .activeBackground(isActive)
            .padding(20.dp, 16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https:$iconUrl")
                .crossfade(true)
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
                color = MaterialTheme.colors.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = buildAnnotatedString {
                    append(temperature.toString())
                    withStyle(unitStyle) {
                        append(stringResource(id = R.string.home_celsius))
                    }
                },
                color = MaterialTheme.colors.primary,
                fontSize = 26.sp
            )
        }
    }
}

@Composable
fun HomeScreenForecastDaily(
    iconUrl: String,
    date: String,
    temperature: Int,
    isActive: Boolean = true,
) {
    val unitStyle = SpanStyle(
        baselineShift = BaselineShift.Superscript,
        fontSize = 14.sp
    )

    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(
                color = MaterialTheme.colors.inactiveBackground,
                shape = MaterialTheme.shapes.medium
            )
            .activeBackground(isActive)
            .padding(20.dp, 16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https:$iconUrl")
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date,
                color = MaterialTheme.colors.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = buildAnnotatedString {
                    append(temperature.toString())
                    withStyle(unitStyle) {
                        append(stringResource(id = R.string.home_celsius))
                    }
                },
                color = MaterialTheme.colors.primary,
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
            dailyForecast = previewHomeScreenDailyForecast(),
            hourlyForecast = previewHomeScreenHourlyForecast(),
        )
    }
}