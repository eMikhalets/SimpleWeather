package com.emikhalets.simpleweather.data.repository

import com.emikhalets.simpleweather.data.remote.ApiService
import com.emikhalets.simpleweather.data.remote.entity.CurrentWeatherResponse
import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse
import com.emikhalets.simpleweather.data.remote.entity.SearchData
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: ApiService,
) : WeatherRepository {

    override suspend fun current(query: String): Result<CurrentWeatherResponse> {
        return runCatching { api.current(query) }
    }

    override suspend fun forecast(query: String): Result<ForecastWeatherResponse> {
        return runCatching { api.forecast(query) }
    }

    override suspend fun search(query: String): Result<List<SearchData>> {
        return runCatching { api.search(query) }
    }
}