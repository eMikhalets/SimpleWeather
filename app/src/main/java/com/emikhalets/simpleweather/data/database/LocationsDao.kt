package com.emikhalets.simpleweather.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LocationsDao {

    @Insert
    suspend fun insert(item: SearchDBEntity): Long

    @Delete
    suspend fun delete(item: SearchDBEntity): Int

    @Update
    suspend fun update(item: SearchDBEntity): Int

    @Query("SELECT * FROM locations")
    suspend fun getAll(): List<SearchDBEntity>
}