package com.emikhalets.simpleweather.ui.screens.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.background
import androidx.glance.appwidget.cornerRadius
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.ui.theme.surfaceTop

class WidgetWeather : GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    @Composable
    override fun Content() {
        val state = currentState<Preferences>()
        val date = state[WidgetWeatherReceiver.KEY_DATE] ?: ""
        val temperature = state[WidgetWeatherReceiver.KEY_TEMP] ?: 0
        val description = state[WidgetWeatherReceiver.KEY_DESC] ?: ""

        WidgetWeatherContent(
            date = date,
            temperature = temperature,
            description = description,
            modifier = GlanceModifier
                .fillMaxSize()
                .background(
                    day = MaterialTheme.colors.surfaceTop,
                    night = MaterialTheme.colors.surfaceTop
                )
                .appWidgetBackground()
                .cornerRadius(16.dp)
                .padding(8.dp)
        )
    }
}

@Composable
fun WidgetWeatherContent(
    date: String,
    temperature: Int,
    description: String,
    modifier: GlanceModifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (date.isEmpty() or description.isEmpty()) {
            Text(
                text = stringResource(R.string.widget_weather_update_error),
                color = MaterialTheme.colors.primary,
                fontSize = 18.sp
            )
        } else {
            Row() {
                Column(
                    modifier = GlanceModifier.fillMaxWidth().defaultWeight()
                ) {
                    Text(
                        text = date,
                        color = MaterialTheme.colors.primary,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.app_unit_celsius_value, temperature),
                        color = MaterialTheme.colors.primary,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = description,
                        color = MaterialTheme.colors.primary,
                        fontSize = 16.sp
                    )
                }
                // TODO: add button
            }
        }
    }
}