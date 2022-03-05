package com.emikhalets.simpleweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.emikhalets.simpleweather.ui.screens.home.HomeScreen
import com.emikhalets.simpleweather.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var lightTheme by remember { mutableStateOf(true) }

            AppTheme(lightTheme) {
                AppScreen {
                    HomeScreen(
                        onThemeChange = { lightTheme = !lightTheme },
                        onForecastNavigate = {}
                    )
                }
            }
        }
    }
}

@Composable
fun AppScreen(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        content()
    }
}