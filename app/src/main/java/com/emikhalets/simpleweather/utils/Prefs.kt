package com.emikhalets.simpleweather.utils

import android.content.Context

class Prefs(context: Context) {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    fun getCurrentLocation(): String {
        return prefs.getString("app_sp_current_location", "") ?: ""
    }

    fun putCurrentLocation(value: String) {
        return prefs.edit().putString("app_sp_current_location", value).apply()
    }
}