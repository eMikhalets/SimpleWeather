package com.emikhalets.simpleweather.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationData(
    @SerialName("country") val country: String?,
    @SerialName("lat") val lat: Double?,
    @SerialName("localtime") val localtime: String?,
    @SerialName("localtime_epoch") val localtime_epoch: Int?,
    @SerialName("lon") val lon: Double?,
    @SerialName("name") val name: String?,
    @SerialName("region") val region: String?,
    @SerialName("tz_id") val tz_id: String?
)