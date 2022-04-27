package com.emikhalets.simpleweather.data.repository

import com.emikhalets.simpleweather.data.remote.ApiService
import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse
import com.emikhalets.simpleweather.data.remote.entity.SearchData
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: ApiService
) : WeatherRepository {

    override suspend fun getWeather(query: String): Result<ForecastWeatherResponse> {
        return runCatching { api.forecast(query) }
    }

    override suspend fun search(query: String): Result<List<SearchData>> {
        return runCatching { api.search(query) }
    }
}