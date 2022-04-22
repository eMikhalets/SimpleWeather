package com.emikhalets.simpleweather.di

import com.emikhalets.simpleweather.data.remote.ApiService
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
}