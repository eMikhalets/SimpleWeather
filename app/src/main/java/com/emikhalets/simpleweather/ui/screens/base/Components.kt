package com.emikhalets.simpleweather.ui.screens.base

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.emikhalets.simpleweather.ui.theme.forecastIcon
import com.emikhalets.simpleweather.ui.theme.primaryText
import com.emikhalets.simpleweather.ui.theme.secondaryText

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