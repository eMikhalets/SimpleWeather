package com.emikhalets.simpleweather.ui.screens.home

import com.emikhalets.simpleweather.ui.screens.base.entity.DailyForecastEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.HomeCurrentEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.HourlyForecastEntity

data class HomeScreenState(
    val current: HomeCurrentEntity? = null,
    val daily: List<DailyForecastEntity> = emptyList(),
    val hourly: List<HourlyForecastEntity> = emptyList(),
    val error: String = ""
)