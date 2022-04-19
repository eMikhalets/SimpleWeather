package com.emikhalets.simpleweather.ui.screens.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.ui.theme.forecastIcon
import com.emikhalets.simpleweather.ui.theme.primaryText
import com.emikhalets.simpleweather.ui.theme.secondaryText

@Composable
fun AppScaffold(navController: NavHostController, content: @Composable () -> Unit) {
    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = { AppBottomBar(navController) },
        content = {
            Box(
                modifier = Modifier.padding(it),
                content = { content() }
            )
        }
    )
}

@Composable
private fun AppTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
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

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null) },
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
fun TextPrimary(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primaryText,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        overflow = overflow,
        maxLines = maxLines,
        modifier = modifier
    )
}

@Composable
fun TextSecondary(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.secondaryText,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        overflow = overflow,
        maxLines = maxLines,
        modifier = modifier
    )
}

@Composable
fun AppIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.forecastIcon,
    size: Dp = 24.dp,
    onClick: () -> Unit = {},
) {
    Icon(
        imageVector = imageVector,
        contentDescription = "",
        tint = color,
        modifier = modifier
            .size(size)
            .clickable { onClick() }
    )
}

@Composable
fun AppIcon(
    imageUrl: String,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    onClick: () -> Unit = {},
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "",
        modifier = modifier
            .size(size)
            .clickable { onClick() }
    )
}