package com.emikhalets.simpleweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.emikhalets.simpleweather.ui.screens.base.AppNavHost
import com.emikhalets.simpleweather.ui.screens.base.AppScaffold
import com.emikhalets.simpleweather.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Application()
        }
    }
}

@Composable
fun Application() {
    val navHost = rememberNavController()

    AppTheme {
        AppScaffold(navHost) {
            AppNavHost(navHost)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ApplicationPreview() {
    Application()
}