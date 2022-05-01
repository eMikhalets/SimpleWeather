package com.emikhalets.simpleweather.data.remote

import com.emikhalets.simpleweather.data.remote.entity.CurrentWeatherResponse
import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse
import com.emikhalets.simpleweather.data.remote.entity.SearchData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
    suspend fun current(
        @Query("q") query: String,
    ): CurrentWeatherResponse

    @GET("forecast.json")
    suspend fun forecast(
        @Query("q") query: String,
        @Query("days") days: Int = 3,
    ): ForecastWeatherResponse

    @GET("search.json")
    suspend fun search(
        @Query("q") query: String,
    ): List<SearchData>
}