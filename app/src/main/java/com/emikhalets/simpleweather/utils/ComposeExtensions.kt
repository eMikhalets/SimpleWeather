package com.emikhalets.simpleweather.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.emikhalets.simpleweather.ui.theme.activeBackground
import com.emikhalets.simpleweather.ui.theme.primaryText
import com.emikhalets.simpleweather.ui.theme.secondaryText
import com.emikhalets.simpleweather.ui.theme.surfaceBottom
import com.emikhalets.simpleweather.ui.theme.surfaceTop

fun Modifier.activeBackground(active: Boolean): Modifier = composed {
    if (active) {
        background(
            color = MaterialTheme.colors.activeBackground,
            shape = RoundedCornerShape(12.dp)
        )
    } else {
        background(color = Color.Transparent)
    }
}

fun Modifier.appSurface(): Modifier = composed {
    background(
        brush = Brush.verticalGradient(
            colors = listOf(
                MaterialTheme.colors.surfaceTop,
                MaterialTheme.colors.surfaceBottom
            )
        )
    )
}

@Composable
fun activeTextColor(active: Boolean): Color {
    return if (active) {
        MaterialTheme.colors.primaryText
    } else {
        MaterialTheme.colors.secondaryText
    }
}