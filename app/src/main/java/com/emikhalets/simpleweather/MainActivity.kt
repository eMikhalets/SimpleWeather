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
import androidx.compose.ui.tooling.preview.Preview
import com.emikhalets.simpleweather.ui.screens.home.HomeScreen
import com.emikhalets.simpleweather.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            var lightTheme by remember { mutableStateOf(true) }

            AppTheme(lightTheme) {
                AppScreen(
                    onThemeChange = { lightTheme = !lightTheme }
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun AppScreen(
    onThemeChange: () -> Unit,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview() {
    AppTheme {
        AppScreen(
            onThemeChange = {}
        ) {
            HomeScreen()
        }
    }
}