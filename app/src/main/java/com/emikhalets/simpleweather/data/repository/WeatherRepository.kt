package com.emikhalets.simpleweather.data.repository

import com.emikhalets.simpleweather.data.remote.entity.CurrentWeatherResponse
import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse
import com.emikhalets.simpleweather.data.remote.entity.SearchData

interface WeatherRepository {

    suspend fun current(query: String): Result<CurrentWeatherResponse>

    suspend fun forecast(query: String): Result<ForecastWeatherResponse>

    suspend fun search(query: String): Result<List<SearchData>>
}
