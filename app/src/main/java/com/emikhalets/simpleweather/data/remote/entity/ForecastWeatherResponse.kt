package com.emikhalets.simpleweather.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeatherResponse(
    @SerialName("current") val current: Current?,
    @SerialName("forecast") val forecast: Forecast?,
    @SerialName("location") val location: LocationData?
)