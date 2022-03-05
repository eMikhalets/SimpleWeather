package com.emikhalets.simpleweather.utils

import com.emikhalets.simpleweather.ui.screens.forecast.DailyForecast

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