package com.emikhalets.simpleweather.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Colors.homeForecastBackground: Color
    get() = if (isLight) Color(0xFFF7F7F7) else Purple700

val Colors.primaryText: Color
    get() = if (isLight) Color(0xFF333333) else Purple700

val Colors.secondaryText: Color
    get() = if (isLight) Color(0xFF777777) else Purple700

val Colors.forecastIcon: Color
    get() = if (isLight) Color(0xFFD2D2D2) else Purple700

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
    lightTheme: Boolean = true,
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