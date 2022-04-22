package com.emikhalets.simpleweather.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    @SerialName("avghumidity") val avghumidity: Double?,
    @SerialName("avgtemp_c") val avgtemp_c: Double?,
    @SerialName("avgtemp_f") val avgtemp_f: Double?,
    @SerialName("avgvis_km") val avgvis_km: Double?,
    @SerialName("avgvis_miles") val avgvis_miles: Double?,
    @SerialName("condition") val condition: Condition?,
    @SerialName("daily_chance_of_rain") val daily_chance_of_rain: Int?,
    @SerialName("daily_chance_of_snow") val daily_chance_of_snow: Int?,
    @SerialName("daily_will_it_rain") val daily_will_it_rain: Int?,
    @SerialName("daily_will_it_snow") val daily_will_it_snow: Int?,
    @SerialName("maxtemp_c") val maxtemp_c: Double?,
    @SerialName("maxtemp_f") val maxtemp_f: Double?,
    @SerialName("maxwind_kph") val maxwind_kph: Double?,
    @SerialName("maxwind_mph") val maxwind_mph: Double?,
    @SerialName("mintemp_c") val mintemp_c: Double?,
    @SerialName("mintemp_f") val mintemp_f: Double?,
    @SerialName("totalprecip_in") val totalprecip_in: Double?,
    @SerialName("totalprecip_mm") val totalprecip_mm: Double?,
    @SerialName("uv") val uv: Double?
)