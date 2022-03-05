package com.emikhalets.simpleweather.ui.screens.forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.ui.screens.base.AppIcon
import com.emikhalets.simpleweather.ui.screens.base.TextPrimary
import com.emikhalets.simpleweather.ui.screens.base.TextSecondary
import com.emikhalets.simpleweather.ui.screens.base.WeatherUnits
import com.emikhalets.simpleweather.ui.theme.AppTheme
import com.emikhalets.simpleweather.utils.getDayOfWeekNamesStartToday
import com.emikhalets.simpleweather.utils.previewDailyForecast
import com.emikhalets.simpleweather.utils.previewDayOfWeekNames
import java.util.*

@Composable
fun ForecastScreen() {
    val dayOfWeekNames by remember {
        mutableStateOf(Calendar.getInstance().getDayOfWeekNamesStartToday(7))
    }

    ForecastScreen(
        dayOfWeekNames = dayOfWeekNames,
        dailyForecast = emptyList(),
        sunriseTime = "",
        sunsetTime = "",
        units = WeatherUnits(),
    )
}

// TODO: align forecast content
@Composable
fun ForecastScreen(
    dayOfWeekNames: List<String>,
    dailyForecast: List<DailyForecast>,
    sunriseTime: String,
    sunsetTime: String,
    units: WeatherUnits,
) {
    val temperatureUnitText = stringResource(units.temperature())
    Column(Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 24.dp)
        ) {
            TextPrimary(
                text = stringResource(R.string.forecast_next_days_weather),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(16.dp))
            repeat(dayOfWeekNames.size) { index ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 12.dp)
                ) {
                    TextSecondary(
                        text = dayOfWeekNames[index],
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        TextPrimary(
                            text = stringResource(
                                R.string.app_value_no_space,
                                dailyForecast[index].maxTemperature,
                                temperatureUnitText
                            ),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        TextSecondary(
                            text = stringResource(
                                R.string.app_value_no_space,
                                dailyForecast[index].minTemperature,
                                temperatureUnitText
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(3f)
                    ) {
                        AppIcon(imageUrl = dailyForecast[index].iconUrl, size = 24.dp)
                        Spacer(modifier = Modifier.width(12.dp))
                        TextSecondary(
                            text = dailyForecast[index].description,
                            fontWeight = FontWeight.Medium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            TextPrimary(
                text = stringResource(R.string.forecast_sun_time),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            SunriseSunsetTime(
                sunriseTime = sunriseTime,
                sunsetTime = sunsetTime
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextPrimary(
                text = stringResource(R.string.forecast_uv_index),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(16.dp))
            UvIndexScale()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        ForecastScreen(
            dayOfWeekNames = previewDayOfWeekNames(),
            dailyForecast = previewDailyForecast(),
            sunriseTime = "",
            sunsetTime = "",
            units = WeatherUnits(),
        )
    }
}