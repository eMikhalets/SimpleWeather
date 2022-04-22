package com.emikhalets.simpleweather.ui.screens.base.entity

data class DailyForecastEntity(
    val iconUrl: String = "",
    val date: String = "",
    val temperature: Int = 0,
)