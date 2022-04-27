package com.emikhalets.simpleweather.data.repository

import com.emikhalets.simpleweather.data.database.SearchDBEntity

interface DatabaseRepository {

    suspend fun insertLocation(entity: SearchDBEntity): Result<Long>

    suspend fun deleteLocation(entity: SearchDBEntity): Result<Int>

    suspend fun updateLocation(entity: SearchDBEntity): Result<Int>

    suspend fun searchByName(query: String): Result<List<SearchDBEntity>>
}
