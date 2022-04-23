package com.emikhalets.simpleweather.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocationsDao {

    @Insert
    suspend fun insert(item: SearchDBEntity): Long

    @Delete
    suspend fun delete(item: SearchDBEntity): Int

    @Query("SELECT * FROM locations WHERE name LIKE '%' || :name || '%'")
    suspend fun searchByName(name: String): List<SearchDBEntity>
}