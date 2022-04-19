package com.emikhalets.simpleweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.emikhalets.simpleweather.ui.screens.base.AppNavHost
import com.emikhalets.simpleweather.ui.screens.base.AppScaffold
import com.emikhalets.simpleweather.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navHost = rememberNavController()

            AppTheme {
                AppScaffold(navHost) {
                    AppNavHost(navHost)
                }
            }
        }
    }
}