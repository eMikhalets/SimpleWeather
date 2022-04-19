package com.emikhalets.simpleweather.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Colors.forecastIcon: Color
    get() = if (isLight) Color(0xFFD2D2D2) else Purple700



val Colors.primaryText: Color
    get() = if (isLight) Color(0xFF000000) else Color(0xFFFFFFFF)

val Colors.secondaryText: Color
    get() = if (isLight) Color(0xFF666666) else Color(0xFFB6B5C5)

val Colors.inactiveBackground: Color
    get() = if (isLight) Color(0xFF000000) else Color.Black.copy(alpha = 0.5f)

val Colors.activeBackground: Color
    get() = if (isLight) Color(0xFF000000) else Color(0xFF1B86E6)

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = Color(0xFF121212),
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    surface = Color.White,

    /* Other default colors to override
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun AppTheme(
    lightTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colors = if (lightTheme) {
        LightColorPalette
    } else {
        DarkColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}