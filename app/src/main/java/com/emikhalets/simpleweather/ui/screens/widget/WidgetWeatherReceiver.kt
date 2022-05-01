package com.emikhalets.simpleweather.ui.screens.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import com.emikhalets.simpleweather.App
import com.emikhalets.simpleweather.data.remote.entity.CurrentWeatherResponse
import com.emikhalets.simpleweather.data.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class WidgetWeatherReceiver : GlanceAppWidgetReceiver() {

    @Inject
    lateinit var remoteRepo: WeatherRepository

    override val glanceAppWidget: GlanceAppWidget = WidgetWeather()

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_UPDATE) {
            updateWeather(context)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        updateWeather(context)
    }

    private fun updateWeather(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            App.prefs?.getCurrentLocation()?.let { location ->
                remoteRepo.current(location)
                    .onSuccess { handleCurrentWeatherSuccess(context, it) }
                    .onFailure { handleCurrentWeatherError(it) }
            }
        }
    }

    private suspend fun handleCurrentWeatherSuccess(
        context: Context,
        weather: CurrentWeatherResponse,
    ) {
        val glanceId = GlanceAppWidgetManager(context)
            .getGlanceIds(WidgetWeather::class.java).firstOrNull()

        glanceId?.let {
            updateAppWidgetState(context, glanceId) { prefs ->
                prefs.toMutablePreferences().apply {
                    this[KEY_DATE] = weather.location?.localtime ?: ""
                    this[KEY_TEMP] = weather.current?.temp_c?.toInt() ?: 0
                    this[KEY_DESC] = weather.current?.condition?.text ?: ""
                }
            }
        }
    }

    private fun handleCurrentWeatherError(throwable: Throwable) {
        // TODO: implement
    }

    companion object {
        const val ACTION_UPDATE = "app_intent_action_update"

        val KEY_DATE = stringPreferencesKey("app_widget_weather_date")
        val KEY_TEMP = intPreferencesKey("app_widget_weather_temperature")
        val KEY_DESC = stringPreferencesKey("app_widget_weather_description")
    }
}