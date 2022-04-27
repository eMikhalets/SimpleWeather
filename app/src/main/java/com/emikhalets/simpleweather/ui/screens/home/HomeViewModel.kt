package com.emikhalets.simpleweather.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse
import com.emikhalets.simpleweather.data.repository.DatabaseRepository
import com.emikhalets.simpleweather.data.repository.WeatherRepository
import com.emikhalets.simpleweather.utils.mappers.ForecastWeatherMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remoteRepo: WeatherRepository,
    private val databaseRepo: DatabaseRepository
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set

    // TODO: define query
    fun getWeather() {
        viewModelScope.launch {
            remoteRepo.getWeather("London")
                .onSuccess { handleSuccessWeatherResponse(it) }
                .onFailure { handleFailureWeatherResponse(it) }

        }
    }

    private suspend fun handleSuccessWeatherResponse(response: ForecastWeatherResponse) {
        state = state.copy(
            current = ForecastWeatherMapper.mapCurrentWeather(response),
            daily = ForecastWeatherMapper.mapDailyForecastWeather(response),
            hourly = ForecastWeatherMapper.mapHourlyForecastWeather(response)
        )
    }

    private fun handleFailureWeatherResponse(throwable: Throwable) {
        state = state.copy(
            error = throwable.message ?: ""
        )
    }
}