package com.emikhalets.simpleweather.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Astro(
    @SerialName("moon_illumination") val moon_illumination: String?,
    @SerialName("moon_phase") val moon_phase: String?,
    @SerialName("moonrise") val moonrise: String?,
    @SerialName("moonset") val moonset: String?,
    @SerialName("sunrise") val sunrise: String?,
    @SerialName("sunset") val sunset: String?
)