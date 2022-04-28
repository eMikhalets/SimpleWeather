package com.emikhalets.simpleweather.utils.mappers

import com.emikhalets.simpleweather.data.database.SearchDBEntity
import com.emikhalets.simpleweather.data.remote.entity.SearchData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object LocationsMapper {

    suspend fun mapRemoteLocations(locations: List<SearchData>): List<SearchDBEntity> {
        return withContext(Dispatchers.IO) {
            locations.map { location ->
                location.id ?: return@withContext emptyList()
                location.name ?: return@withContext emptyList()
                location.region ?: return@withContext emptyList()
                location.country ?: return@withContext emptyList()
                location.latitude ?: return@withContext emptyList()
                location.longitude ?: return@withContext emptyList()

                SearchDBEntity(
                    id = location.id,
                    name = location.name,
                    region = location.region,
                    country = location.country,
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            }
        }
    }
}