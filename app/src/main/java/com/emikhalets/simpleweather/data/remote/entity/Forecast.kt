package com.emikhalets.simpleweather.data.remote.entity

import kotlinx.serialization.SerialName

data class Forecast(
    @SerialName("forecastDay") val forecastDay: List<ForecastDay>?
)