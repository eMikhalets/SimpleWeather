package com.emikhalets.simpleweather.ui.screens.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emikhalets.simpleweather.ui.screens.home.HomeScreen
import com.emikhalets.simpleweather.ui.screens.search.SearchScreen
import com.emikhalets.simpleweather.ui.screens.settings.SettingsScreen

sealed class AppScreen(val route: String, val icon: ImageVector) {
    object Home : AppScreen("home", Icons.Default.Home)
    object Search : AppScreen("search", Icons.Default.Search)
    object Settings : AppScreen("settings", Icons.Default.Settings)
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, AppScreen.Home.route) {
        composable(AppScreen.Home.route) {
            HomeScreen({}, {})
        }
        composable(AppScreen.Search.route) {
            SearchScreen()
        }
        composable(AppScreen.Settings.route) {
            SettingsScreen()
        }
    }
}