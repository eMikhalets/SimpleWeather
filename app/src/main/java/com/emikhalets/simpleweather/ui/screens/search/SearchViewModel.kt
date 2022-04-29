package com.emikhalets.simpleweather.ui.screens.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emikhalets.simpleweather.App
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.data.database.SearchDBEntity
import com.emikhalets.simpleweather.data.remote.entity.SearchData
import com.emikhalets.simpleweather.data.repository.DatabaseRepository
import com.emikhalets.simpleweather.data.repository.WeatherRepository
import com.emikhalets.simpleweather.utils.UiString
import com.emikhalets.simpleweather.utils.mappers.LocationsMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val remoteRepo: WeatherRepository,
    private val databaseRepo: DatabaseRepository
) : ViewModel() {

    var state by mutableStateOf(SearchScreenState())
        private set

    private var searchingJob: Job? = null

    fun getLocations(query: String = "") {
        try {
            searchingJob?.cancel()
            searchingJob = viewModelScope.launch {
                delay(750)
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
        } catch (ex: CancellationException) {
            Timber.e(ex)
        }
    }

    fun addLocation(index: Int) {
        viewModelScope.launch {
            try {
                databaseRepo.insertLocation(state.locations[index])
                    .onSuccess { handleSuccessAddLocationResponse(it) }
                    .onFailure { handleFailureResponse(it) }
            } catch (indexEx: IndexOutOfBoundsException) {
                Timber.e(indexEx)
                handleIndexOutOfBoundsError(index)
            }
        }
    }

    fun addLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val entity = SearchDBEntity(
                id = 1,
                name = "My location",
                region = "My Region",
                country = "My Country",
                latitude = latitude,
                longitude = longitude
            )
            databaseRepo.insertLocation(entity)
                .onSuccess { handleSuccessAddLocationResponse(it) }
                .onFailure { handleFailureResponse(it) }
        }
    }

    // TODO: add delete button in ui
    fun deleteLocation(location: SearchDBEntity) {
        viewModelScope.launch {
            databaseRepo.deleteLocation(location)
                .onSuccess { handleSuccessDeleteLocationResponse(it) }
                .onFailure { handleFailureResponse(it) }
        }
    }

    fun saveLocationPref(index: Int) {
        viewModelScope.launch {
            try {
                App.prefs?.putCurrentLocation(state.locations[index].coords)
            } catch (indexEx: IndexOutOfBoundsException) {
                Timber.e(indexEx)
                handleIndexOutOfBoundsError(index)
            }
        }
    }

    private fun handleSuccessDBLocationsResponse(locations: List<SearchDBEntity>) {
        state = state.copy(
            locations = locations
                .sortedWith(compareBy(SearchDBEntity::country, SearchDBEntity::name))
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

    private fun handleIndexOutOfBoundsError(index: Int) {
        state = state.copy(
            error = UiString.ResourceString(R.string.search_no_location_by_num_error, index)
        )
    }
}