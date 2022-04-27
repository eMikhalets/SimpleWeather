package com.emikhalets.simpleweather.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchData(
    @SerialName("id") val id: Int?,
    @SerialName("name") val name: String?,
    @SerialName("region") val region: String?,
    @SerialName("country") val country: String?,
    @SerialName("lat") val latitude: Double?,
    @SerialName("lon") val longitude: Double?,
    @SerialName("url") val url: String?,
)