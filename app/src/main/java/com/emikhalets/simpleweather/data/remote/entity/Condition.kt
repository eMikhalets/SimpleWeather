package com.emikhalets.simpleweather.data.remote.entity

import kotlinx.serialization.SerialName

data class Condition(
    @SerialName("code") val code: Int?,
    @SerialName("icon") val icon: String?,
    @SerialName("text") val text: String?
)