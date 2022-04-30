package com.emikhalets.simpleweather.ui.screens.base

import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emikhalets.simpleweather.ui.screens.home.HomeScreen
import com.emikhalets.simpleweather.ui.screens.search.SearchScreen
import com.emikhalets.simpleweather.ui.screens.settings.SettingsScreen
import com.emikhalets.simpleweather.utils.LocationHelper

sealed class AppScreen(val route: String, val icon: ImageVector) {
    object Home : AppScreen("home", Icons.Default.Home)
    object Search : AppScreen("search", Icons.Default.Search)
    object Settings : AppScreen("settings", Icons.Default.Settings)
}

// TODO: navigate back from search screen doesn't close app
@Composable
fun AppNavHost(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    locationHelper: LocationHelper?
) {
    NavHost(navController, AppScreen.Home.route) {
        composable(AppScreen.Home.route) {
            HomeScreen(
                viewModel = hiltViewModel(),
                scaffoldState = scaffoldState,
            )
        }
        composable(AppScreen.Search.route) {
            SearchScreen(
                viewModel = hiltViewModel(),
                scaffoldState = scaffoldState,
                locationHelper = locationHelper
            )
        }
        composable(AppScreen.Settings.route) {
            SettingsScreen()
        }
    }
}