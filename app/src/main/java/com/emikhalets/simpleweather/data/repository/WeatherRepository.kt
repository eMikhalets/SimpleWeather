package com.emikhalets.simpleweather.data.repository

import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse

interface WeatherRepository {

    suspend fun getWeather(query: String): Result<ForecastWeatherResponse>
}
