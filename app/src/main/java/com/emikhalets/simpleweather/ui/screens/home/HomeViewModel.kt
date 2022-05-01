package com.emikhalets.simpleweather.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emikhalets.simpleweather.App.Companion.prefs
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.data.remote.entity.ForecastWeatherResponse
import com.emikhalets.simpleweather.data.repository.DatabaseRepository
import com.emikhalets.simpleweather.data.repository.WeatherRepository
import com.emikhalets.simpleweather.utils.UiString
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

    fun getWeather() {
        viewModelScope.launch {
            val location = prefs?.getCurrentLocation()
            if (!location.isNullOrEmpty()) {
                remoteRepo.forecast(location)
                    .onSuccess { handleSuccessWeatherResponse(it) }
                    .onFailure { handleFailureResponse(it) }
            } else {
                state = state.copy(
                    error = UiString.ResourceString(R.string.prefs_no_saved_location)
                )
            }
        }
    }

    private suspend fun handleSuccessWeatherResponse(response: ForecastWeatherResponse) {
        state = state.copy(
            current = ForecastWeatherMapper.mapCurrentWeather(response),
            daily = ForecastWeatherMapper.mapDailyForecastWeather(response),
            hourly = ForecastWeatherMapper.mapHourlyForecastWeather(response)
        )
    }

    private fun handleFailureResponse(throwable: Throwable) {
        val message = throwable.message?.let { str -> UiString.DynamicString(str) }
            ?: UiString.ResourceString(R.string.database_unexpected_error)

        state = state.copy(
            error = message
        )
    }
}