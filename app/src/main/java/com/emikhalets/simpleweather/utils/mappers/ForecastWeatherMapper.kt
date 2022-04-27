package com.emikhalets.simpleweather.utils.mappers

import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse
import com.emikhalets.simpleweather.ui.screens.base.entity.DailyForecastEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.HomeCurrentEntity
import com.emikhalets.simpleweather.ui.screens.base.entity.HourlyForecastEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

object ForecastWeatherMapper {

    suspend fun mapCurrentWeather(response: ForecastWeatherResponse): HomeCurrentEntity? {
        return withContext(Dispatchers.IO) {
            response.location?.name ?: return@withContext null
            response.location.localtime ?: return@withContext null
            response.current?.condition?.icon ?: return@withContext null
            response.current.temp_c ?: return@withContext null
            response.current.wind_kph ?: return@withContext null
            response.current.humidity ?: return@withContext null

            HomeCurrentEntity(
                location = response.location.name,
                date = response.location.localtime,
                iconUrl = response.current.condition.icon,
                temperature = response.current.temp_c.toInt(),
                windSpeed = response.current.wind_kph.toInt(),
                humidity = response.current.humidity
            )
        }
    }

    suspend fun mapDailyForecastWeather(
        response: ForecastWeatherResponse
    ): List<DailyForecastEntity> {
        return withContext(Dispatchers.IO) {
            response.forecast?.forecastDay ?: return@withContext emptyList()

            response.forecast.forecastDay.map { fDay ->
                if (fDay.day?.condition?.icon != null &&
                    fDay.date_epoch != null &&
                    fDay.day.avgtemp_c != null
                ) {
                    DailyForecastEntity(
                        iconUrl = fDay.day.condition.icon,
                        date = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
                            .format(fDay.date_epoch),
                        temperature = fDay.day.avgtemp_c.toInt()
                    )
                } else {
                    DailyForecastEntity()
                }
            }
        }
    }

    suspend fun mapHourlyForecastWeather(
        response: ForecastWeatherResponse
    ): List<HourlyForecastEntity> {
        return withContext(Dispatchers.IO) {
            response.forecast?.forecastDay ?: return@withContext emptyList()

            response.forecast.forecastDay.flatMap { fDay ->
                fDay.hour ?: return@flatMap emptyList()

                fDay.hour.map { hour ->
                    if (hour.condition?.icon != null &&
                        hour.time != null &&
                        hour.temp_c != null
                    ) {
                        HourlyForecastEntity(
                            iconUrl = hour.condition.icon,
                            time = hour.time.split(" ")[1],
                            temperature = hour.temp_c.toInt()
                        )
                    } else {
                        HourlyForecastEntity()
                    }
                }
            }
        }
    }
}