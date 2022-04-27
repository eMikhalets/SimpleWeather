package com.emikhalets.simpleweather.data.repository

import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse
import com.emikhalets.simpleweather.data.remote.entity.SearchData

interface WeatherRepository {

    suspend fun getWeather(query: String): Result<ForecastWeatherResponse>

    suspend fun search(query: String): Result<List<SearchData>>
}
