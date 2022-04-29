package com.emikhalets.simpleweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.emikhalets.simpleweather.ui.screens.base.AppNavHost
import com.emikhalets.simpleweather.ui.screens.base.AppScaffold
import com.emikhalets.simpleweather.ui.theme.AppTheme
import com.emikhalets.simpleweather.utils.LocationHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var locationHelper: LocationHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Application(locationHelper) }
    }

    override fun onStart() {
        super.onStart()
        locationHelper = LocationHelper(this)
    }
}

@Composable
fun Application(locationHelper: LocationHelper?) {
    val navHost = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    AppTheme {
        AppScaffold(navHost) {
            AppNavHost(
                navController = navHost,
                scaffoldState = scaffoldState,
                locationHelper = locationHelper
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ApplicationPreview() {
    Application(locationHelper = LocationHelper(LocalContext.current))
}