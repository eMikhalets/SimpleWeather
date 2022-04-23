package com.emikhalets.simpleweather.di

import android.content.Context
import com.emikhalets.simpleweather.data.database.AppDatabase
import com.emikhalets.simpleweather.data.database.LocationsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.get(context)
    }

    @Singleton
    @Provides
    fun providesLocationsDao(database: AppDatabase): LocationsDao {
        return database.locationsDao
    }
}