package com.emikhalets.simpleweather.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    @SerialName("astro") val astro: Astro?,
    @SerialName("date") val date: String?,
    @SerialName("date_epoch") val date_epoch: Int?,
    @SerialName("day") val day: Day?,
    @SerialName("hour") val hour: List<Hour>?
)