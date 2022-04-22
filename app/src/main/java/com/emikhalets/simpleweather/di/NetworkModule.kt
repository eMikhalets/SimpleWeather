package com.emikhalets.simpleweather.di

import com.emikhalets.simpleweather.data.remote.ApiFactory
import com.emikhalets.simpleweather.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun bindApiService(): ApiService {
        return ApiFactory.getService()
    }
}