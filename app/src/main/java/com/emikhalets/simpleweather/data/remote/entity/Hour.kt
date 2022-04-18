package com.emikhalets.simpleweather.data.remote.entity

import kotlinx.serialization.SerialName

data class Hour(
    @SerialName("chance_of_rain") val chance_of_rain: Int?,
    @SerialName("chance_of_snow") val chance_of_snow: Int?,
    @SerialName("cloud") val cloud: Int?,
    @SerialName("condition") val condition: Condition?,
    @SerialName("dewpoint_c") val dewpoint_c: Double?,
    @SerialName("dewpoint_f") val dewpoint_f: Double?,
    @SerialName("feelslike_c") val feelslike_c: Double?,
    @SerialName("feelslike_f") val feelslike_f: Double?,
    @SerialName("gust_kph") val gust_kph: Double?,
    @SerialName("gust_mph") val gust_mph: Double?,
    @SerialName("heatindex_c") val heatindex_c: Double?,
    @SerialName("heatindex_f") val heatindex_f: Double?,
    @SerialName("humidity") val humidity: Int?,
    @SerialName("is_day") val is_day: Int?,
    @SerialName("precip_in") val precip_in: Double?,
    @SerialName("precip_mm") val precip_mm: Double?,
    @SerialName("pressure_in") val pressure_in: Double?,
    @SerialName("pressure_mb") val pressure_mb: Double?,
    @SerialName("temp_c") val temp_c: Double?,
    @SerialName("temp_f") val temp_f: Double?,
    @SerialName("time") val time: String?,
    @SerialName("time_epoch") val time_epoch: Int?,
    @SerialName("uv") val uv: Double?,
    @SerialName("vis_km") val vis_km: Double?,
    @SerialName("vis_miles") val vis_miles: Double?,
    @SerialName("will_it_rain") val will_it_rain: Int?,
    @SerialName("will_it_snow") val will_it_snow: Int?,
    @SerialName("wind_degree") val wind_degree: Int?,
    @SerialName("wind_dir") val wind_dir: String?,
    @SerialName("wind_kph") val wind_kph: Double?,
    @SerialName("wind_mph") val wind_mph: Double?,
    @SerialName("windchill_c") val windchill_c: Double?,
    @SerialName("windchill_f") val windchill_f: Double?
)