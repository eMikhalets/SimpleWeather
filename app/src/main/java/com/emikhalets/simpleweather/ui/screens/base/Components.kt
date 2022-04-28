package com.emikhalets.simpleweather.ui.screens.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.emikhalets.simpleweather.ui.theme.surfaceBottom
import com.emikhalets.simpleweather.utils.extensions.appSurface

@Composable
fun AppScaffold(navController: NavHostController, content: @Composable () -> Unit) {
    Scaffold(
        bottomBar = { AppBottomBar(navController) },
        content = {
            Box(
                modifier = Modifier
                    .appSurface()
                    .padding(it),
                content = { content() }
            )
        }
    )
}

@Composable
private fun AppBottomBar(navController: NavHostController) {
    val screens = listOf(
        AppScreen.Home,
        AppScreen.Search,
        AppScreen.Settings,
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.surfaceBottom,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = MaterialTheme.colors.secondary,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}