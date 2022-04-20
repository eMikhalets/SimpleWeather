package com.emikhalets.simpleweather.utils

import com.emikhalets.simpleweather.ui.screens.base.entity.HourlyForecastEntity

fun previewHomeScreenHourlyForecast(): List<HourlyForecastEntity> {
    return listOf(
        HourlyForecastEntity("", "11:00", 20),
        HourlyForecastEntity("", "12:00", 20),
        HourlyForecastEntity("", "13:00", 20),
        HourlyForecastEntity("", "14:00", 20),
        HourlyForecastEntity("", "15:00", 20),
        HourlyForecastEntity("", "16:00", 20),
        HourlyForecastEntity("", "17:00", 20),
    )
}