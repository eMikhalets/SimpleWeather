package com.emikhalets.simpleweather.utils.extensions

import com.emikhalets.simpleweather.data.database.SearchDBEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.DailyForecastEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.HourlyForecastEntity

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