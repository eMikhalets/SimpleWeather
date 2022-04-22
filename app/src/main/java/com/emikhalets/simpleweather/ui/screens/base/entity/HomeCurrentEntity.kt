package com.emikhalets.simpleweather.ui.screens.base.entity

data class HomeCurrentEntity(
    val location: String,
    val date: String,
    val iconUrl: String,
    val temperature: Int,
    val windSpeed: Int,
    val humidity: Int,
)