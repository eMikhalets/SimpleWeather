package com.emikhalets.simpleweather.di

import com.emikhalets.simpleweather.data.database.LocationsDao
import com.emikhalets.simpleweather.data.remote.ApiService
import com.emikhalets.simpleweather.data.repository.DatabaseRepository
import com.emikhalets.simpleweather.data.repository.DatabaseRepositoryImpl
import com.emikhalets.simpleweather.data.repository.WeatherRepository
import com.emikhalets.simpleweather.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun bindWeatherRepository(api: ApiService): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun bindDatabaseRepository(locationsDao: LocationsDao): DatabaseRepository {
        return DatabaseRepositoryImpl(locationsDao)
    }
}