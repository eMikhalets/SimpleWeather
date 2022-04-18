package com.emikhalets.simpleweather.data.remote.entity

import kotlinx.serialization.SerialName

data class CurrentWeatherResponse(
    @SerialName("current") val current: Current?,
    @SerialName("location") val location: LocationData?
)