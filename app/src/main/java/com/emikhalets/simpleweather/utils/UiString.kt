package com.emikhalets.simpleweather.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiString {

    data class DynamicString(val string: String) : UiString()

    class ResourceString(
        @StringRes val res: Int,
        vararg val args: Any
    ) : UiString()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> string
            is ResourceString -> stringResource(res, *args)
        }
    }
}