package com.emikhalets.simpleweather.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse
import com.emikhalets.simpleweather.data.repository.WeatherRepository
import com.emikhalets.simpleweather.ui.screens.base.entity.DailyForecastEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.HomeCurrentEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.HourlyForecastEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: WeatherRepository
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set

    fun getWeather() {
        viewModelScope.launch {
            val response = repo.getWeather("London")
            state = HomeScreenState(
                current = mapCurrentWeather(response),
                daily = mapDailyForecastWeather(response),
                hourly = mapHourlyForecastWeather(response)
            )
        }
    }

    private fun mapCurrentWeather(response: ForecastWeatherResponse): HomeCurrentEntity? {
        response.location?.name ?: return null
        response.location.localtime ?: return null
        response.current?.condition?.icon ?: return null
        response.current.temp_c ?: return null
        response.current.wind_kph ?: return null
        response.current.humidity ?: return null

        return HomeCurrentEntity(
            location = response.location.name,
            date = response.location.localtime,
            iconUrl = response.current.condition.icon,
            temperature = response.current.temp_c.toInt(),
            windSpeed = response.current.wind_kph.toInt(),
            humidity = response.current.humidity
        )
    }

    private fun mapDailyForecastWeather(
        response: ForecastWeatherResponse
    ): List<DailyForecastEntity> {
        response.forecast?.forecastDay ?: return emptyList()

        return response.forecast.forecastDay.map { fDay ->
            if (fDay.day?.condition?.icon != null &&
                fDay.date != null &&
                fDay.day.avgtemp_c != null
            ) {
                DailyForecastEntity(
                    iconUrl = fDay.day.condition.icon,
                    date = fDay.date,
                    temperature = fDay.day.avgtemp_c.toInt()
                )
            } else {
                DailyForecastEntity()
            }
        }
    }

    private fun mapHourlyForecastWeather(
        response: ForecastWeatherResponse
    ): List<HourlyForecastEntity> {
        response.forecast?.forecastDay ?: return emptyList()

        return response.forecast.forecastDay.flatMap { fDay ->
            fDay.hour ?: return@flatMap emptyList()

            fDay.hour.map { hour ->
                if (hour.condition?.icon != null &&
                    hour.time != null &&
                    hour.temp_c != null
                ) {
                    HourlyForecastEntity(
                        iconUrl = hour.condition.icon,
                        time = hour.time,
                        temperature = hour.temp_c.toInt()
                    )
                } else {
                    HourlyForecastEntity()
                }
            }
        }
    }
}