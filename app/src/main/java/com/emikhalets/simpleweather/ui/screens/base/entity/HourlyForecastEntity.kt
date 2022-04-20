package com.emikhalets.simpleweather.ui.screens.base.entity

data class HourlyForecastEntity(
    val iconUrl: String,
    val time: String,
    val temperature: Int,
)