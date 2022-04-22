package com.emikhalets.simpleweather.utils

import com.emikhalets.simpleweather.ui.screens.base.entity.HourlyForecastEntity
import com.emikhalets.simpleweather.ui.screens.search.SearchEntity

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

fun previewSearchScreenLocationList(): List<SearchEntity> {
    return listOf(
        SearchEntity("London", "London", "UK"),
        SearchEntity("London", "London", "UK"),
        SearchEntity("London", "London", "UK"),
        SearchEntity("London", "London", "UK"),
    )
}