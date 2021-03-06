package com.emikhalets.simpleweather.utils.extensions

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.emikhalets.simpleweather.ui.theme.activeBackground
import com.emikhalets.simpleweather.ui.theme.surfaceBottom
import com.emikhalets.simpleweather.ui.theme.surfaceTop
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Modifier.activeBackground(active: Boolean): Modifier = composed {
    if (active) {
        background(
            color = MaterialTheme.colors.activeBackground,
            shape = MaterialTheme.shapes.medium
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
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.secondary
    }
}

fun ScaffoldState.showSnackBar(message: String) {
    CoroutineScope(Dispatchers.IO).launch {
        snackbarHostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short
        )
    }
}

fun ScaffoldState.showSnackBar(context: Context, @StringRes resource: Int) {
    CoroutineScope(Dispatchers.IO).launch {
        snackbarHostState.showSnackbar(
            message = context.getString(resource),
            duration = SnackbarDuration.Short
        )
    }
}