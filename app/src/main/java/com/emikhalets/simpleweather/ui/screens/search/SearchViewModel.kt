package com.emikhalets.simpleweather.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.data.database.SearchDBEntity
import com.emikhalets.simpleweather.data.remote.entity.SearchData
import com.emikhalets.simpleweather.data.repository.DatabaseRepository
import com.emikhalets.simpleweather.data.repository.WeatherRepository
import com.emikhalets.simpleweather.utils.UiString
import com.emikhalets.simpleweather.utils.mappers.LocationsMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val remoteRepo: WeatherRepository,
    private val databaseRepo: DatabaseRepository
) : ViewModel() {

    var state by mutableStateOf(SearchScreenState())
        private set

    fun getLocations(query: String = "") {
        viewModelScope.launch {
            when {
                query.isEmpty() -> {
                    databaseRepo.getAll()
                        .onSuccess { handleSuccessDBLocationsResponse(it) }
                        .onFailure { handleFailureResponse(it) }
                }
                else -> {
                    remoteRepo.search(query)
                        .onSuccess { handleSuccessRemoteLocationsResponse(it) }
                        .onFailure { handleFailureResponse(it) }
                }
            }
        }
    }

    fun addLocation(location: SearchDBEntity) {
        viewModelScope.launch {
            databaseRepo.insertLocation(location)
                .onSuccess { handleSuccessAddLocationResponse(it) }
                .onFailure { handleFailureResponse(it) }
        }
    }

    fun deleteLocation(location: SearchDBEntity) {
        viewModelScope.launch {
            databaseRepo.deleteLocation(location)
                .onSuccess { handleSuccessDeleteLocationResponse(it) }
                .onFailure { handleFailureResponse(it) }
        }
    }

    private fun handleSuccessDBLocationsResponse(locations: List<SearchDBEntity>) {
        state = state.copy(
            locations = locations
        )
    }

    private suspend fun handleSuccessRemoteLocationsResponse(locations: List<SearchData>) {
        state = state.copy(
            locations = LocationsMapper.mapRemoteLocations(locations)
        )
    }

    private fun handleSuccessAddLocationResponse(index: Long) {
        when {
            index <= 0 -> {
                state = state.copy(
                    error = UiString.ResourceString(R.string.database_insert_error)
                )
            }
            else -> {
                getLocations()
            }
        }
    }

    private fun handleSuccessDeleteLocationResponse(count: Int) {
        when (count) {
            0 -> {
                state = state.copy(
                    error = UiString.ResourceString(R.string.database_delete_error)
                )
            }
            else -> {
                getLocations()
            }
        }
    }

    private fun handleFailureResponse(throwable: Throwable) {
        val message = throwable.message?.let { str -> UiString.DynamicString(str) }
            ?: UiString.ResourceString(R.string.database_unexpected_error)
        state = state.copy(
            error = message
        )
    }
}