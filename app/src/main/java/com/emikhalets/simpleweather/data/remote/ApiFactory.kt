package com.emikhalets.simpleweather.data.remote

import com.emikhalets.simpleweather.utils.tempApiKey
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

object ApiFactory {

    private const val BASE_URL = "http://api.weatherapi.com/v1/"

    private val contentType = "application/json".toMediaType()

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    private val apiKeyInterceptor = Interceptor { chain ->
        val request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("key", tempApiKey).build()
        val newRequest = request.newBuilder().url(url).build()
        val response = chain.proceed(newRequest)
        response
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

    fun getService(): ApiService = retrofit.create()
}