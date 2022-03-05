package com.emikhalets.simpleweather.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.emikhalets.simpleweather.ui.theme.homeForecastBackground

@Composable
fun HomeScreen(
    onThemeChange: () -> Unit,
    onForecastNavigate: () -> Unit,
) {
    HomeScreen(
        locationName = "Calicut, Kerala",
        dateString = "Sundat, 1 AM",
        temperature = 28,
        weatherIconUrl = "",
        weatherDescription = "Partly Cloudy",
        pressureValue = 810,
        windSpeedValue = 5,
        humidityValue = 94,
        hourlyForecastList = emptyList(),
        units = WeatherUnits(),
        onThemeChangeClick = onThemeChange,
        onForecastClick = onForecastNavigate,
    )
}

@Composable
fun HomeScreen(
    locationName: String,
    dateString: String,
    temperature: Int,
    weatherIconUrl: String,
    weatherDescription: String,
    pressureValue: Int,
    windSpeedValue: Int,
    humidityValue: Int,
    hourlyForecastList: List<HourlyForecast>,
    units: WeatherUnits,
    onThemeChangeClick: () -> Unit,
    onForecastClick: () -> Unit,
) {
    val temperatureText = stringResource(
        R.string.app_value_no_space, temperature, stringResource(units.temperature.unit)
    )
    val pressureText = stringResource(
        R.string.app_value_no_space, pressureValue, stringResource(units.pressure.unit)
    )
    val windSpeedText = stringResource(
        R.string.app_value_with_space, windSpeedValue, stringResource(units.speed.unit)
    )
    val humidityText = stringResource(R.string.app_value_no_space, humidityValue, "%")

    Column(Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                TextPrimary(text = locationName, fontSize = 28.sp, fontWeight = FontWeight.Medium)
                TextSecondary(text = dateString, fontSize = 18.sp)
            }
            AppIcon(
                imageVector = Icons.Default.Menu,
                size = 32.dp,
                onClick = onForecastClick
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            AppIcon(imageUrl = weatherIconUrl, size = 120.dp)
            TextPrimary(text = temperatureText, fontSize = 52.sp, fontWeight = FontWeight.SemiBold)
            TextSecondary(text = weatherDescription, fontSize = 18.sp)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .background(
                    color = MaterialTheme.colors.homeForecastBackground,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
        ) {
            TextPrimary(
                text = stringResource(R.string.home_today),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(Modifier.fillMaxWidth()) {
                HomeWeatherValue(stringResource(R.string.home_pressure), pressureText)
                HomeWeatherValue(stringResource(R.string.home_wind_speed), windSpeedText)
                HomeWeatherValue(stringResource(R.string.home_humidity), humidityText)
            }
            HourlyWeatherGraph(
                hourlyForecastList = hourlyForecastList
            )
        }
    }
}

@Composable
fun RowScope.HomeWeatherValue(text: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        TextSecondary(text = text, fontSize = 16.sp, fontWeight = FontWeight.Normal)
        Spacer(modifier = Modifier.height(4.dp))
        TextPrimary(text = value, fontSize = 20.sp, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(
            locationName = "Calicut, Kerala",
            dateString = "Sunday, 1 AM",
            temperature = 28,
            weatherIconUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fmyicons.co%2F&psig=AOvVaw2fTL5kuQHMqrfP7zdUPPgS&ust=1646499697299000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCNiSl773rPYCFQAAAAAdAAAAABAO",
            weatherDescription = "Partly Cloudy",
            pressureValue = 810,
            windSpeedValue = 5,
            humidityValue = 94,
            hourlyForecastList = emptyList(),
            units = WeatherUnits(),
            onThemeChangeClick = {},
            onForecastClick = {},
        )
    }
}