package com.emikhalets.simpleweather.data.repository

import com.emikhalets.simpleweather.data.database.LocationsDao
import com.emikhalets.simpleweather.data.database.SearchDBEntity
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val locationDao: LocationsDao
) : DatabaseRepository {

    override suspend fun insertLocation(entity: SearchDBEntity): Result<Long> {
        return runCatching { locationDao.insert(entity) }
    }

    override suspend fun deleteLocation(entity: SearchDBEntity): Result<Int> {
        return runCatching { locationDao.delete(entity) }
    }

    override suspend fun updateLocation(entity: SearchDBEntity): Result<Int> {
        return runCatching { locationDao.update(entity) }
    }

    override suspend fun getAll(): Result<List<SearchDBEntity>> {
        return runCatching { locationDao.getAll() }
    }
}