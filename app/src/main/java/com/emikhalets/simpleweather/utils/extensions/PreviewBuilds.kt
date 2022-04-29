package com.emikhalets.simpleweather.utils.extensions

import com.emikhalets.simpleweather.data.database.SearchDBEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.DailyForecastEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.HomeCurrentEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.HourlyForecastEntity

fun previewHomeScreenCurrent(): HomeCurrentEntity {
    return HomeCurrentEntity(
        location = "San Fransisco",
        date = "May 28, 2021",
        iconUrl = "",
        temperature = 28,
        windSpeed = 10,
        humidity = 75
    )
}

fun previewHomeScreenDailyForecast(): List<DailyForecastEntity> {
    return listOf(
        DailyForecastEntity("", "01.02.2022", 20),
        DailyForecastEntity("", "01.02.2022", 20),
        DailyForecastEntity("", "01.02.2022", 20),
    )
}

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

fun previewSearchScreenLocationList(): List<SearchDBEntity> {
    return listOf(
        SearchDBEntity(0, "London", "London", "UK", 0.0, 0.0),
        SearchDBEntity(0, "London", "London", "UK", 0.0, 0.0),
        SearchDBEntity(0, "London", "London", "UK", 0.0, 0.0),
        SearchDBEntity(0, "London", "London", "UK", 0.0, 0.0),
    )
}