package com.emikhalets.simpleweather.utils

import com.emikhalets.simpleweather.ui.screens.forecast.DailyForecast
import com.emikhalets.simpleweather.ui.screens.home.HomeScreenHourlyEntity

fun previewDayOfWeekNames(): List<String> {
    return listOf("WED", "THU", "FRI", "SAT", "SUN", "MON", "TUE")
}

fun previewDailyForecast(): List<DailyForecast> {
    return listOf(
        DailyForecast(24, 18, "Rainy", ""),
        DailyForecast(24, 18, "Isolated thunderere", ""),
        DailyForecast(26, 19, "Cloudy", ""),
        DailyForecast(26, 19, "Mostly Cloudy", ""),
        DailyForecast(26, 19, "Cloudy", ""),
        DailyForecast(24, 18, "Isolated thunderere", ""),
        DailyForecast(26, 19, "Cloudy", "")
    )
}

fun previewHomeScreenHourlyForecast(): List<HomeScreenHourlyEntity> {
    return listOf(
        HomeScreenHourlyEntity("", "11:00", 20),
        HomeScreenHourlyEntity("", "12:00", 20),
        HomeScreenHourlyEntity("", "13:00", 20),
        HomeScreenHourlyEntity("", "14:00", 20),
        HomeScreenHourlyEntity("", "15:00", 20),
        HomeScreenHourlyEntity("", "16:00", 20),
        HomeScreenHourlyEntity("", "17:00", 20),
    )
}