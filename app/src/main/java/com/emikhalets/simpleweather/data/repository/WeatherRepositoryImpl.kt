package com.emikhalets.simpleweather.data.repository

import com.emikhalets.simpleweather.data.remote.ApiService
import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: ApiService
) : WeatherRepository {

    override suspend fun getWeather(query: String): ForecastWeatherResponse {
        return api.forecast(query)
    }
}