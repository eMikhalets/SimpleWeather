package com.emikhalets.simpleweather.ui.screens.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.ui.screens.base.entity.HourlyForecastEntity
import com.emikhalets.simpleweather.ui.theme.primaryText
import com.emikhalets.simpleweather.ui.theme.secondaryText
import com.emikhalets.simpleweather.ui.theme.surfaceBottom
import com.emikhalets.simpleweather.utils.activeBackground
import com.emikhalets.simpleweather.utils.appSurface

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
                selectedContentColor = MaterialTheme.colors.primaryText,
                unselectedContentColor = MaterialTheme.colors.secondaryText,
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

@Composable
fun HourlyForecast(
    hourlyForecast: List<HourlyForecastEntity>,
    rightText: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_today),
                fontSize = 24.sp,
                color = MaterialTheme.colors.primaryText,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            rightText()
        }
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(hourlyForecast) { entity ->
            HourlyForecastBlock(
                iconUrl = entity.iconUrl,
                time = entity.time,
                temperature = entity.temperature,
            )
        }
    }
}

@Composable
fun HourlyForecastBlock(
    iconUrl: String,
    time: String,
    temperature: Int,
    isActive: Boolean = true,
) {
    val tempUnitStyle = SpanStyle(
        baselineShift = BaselineShift.Superscript,
        fontSize = 14.sp
    )

    Row(
        modifier = Modifier
            .padding(8.dp)
            .activeBackground(isActive)
            .padding(20.dp, 16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(iconUrl)
                .crossfade(true)
                .placeholder(R.drawable.app_icon)
                .build(),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = time,
                color = MaterialTheme.colors.primaryText,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = buildAnnotatedString {
                    append(temperature.toString())
                    withStyle(tempUnitStyle) {
                        append(stringResource(id = R.string.home_celsius))
                    }
                },
                color = MaterialTheme.colors.primaryText,
                fontSize = 26.sp
            )
        }
    }
}