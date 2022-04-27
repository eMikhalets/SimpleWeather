package com.emikhalets.simpleweather.utils

import android.content.Context
import androidx.annotation.StringRes

val tempApiKey = "ccfcbe4877b2425ea06153509221804"

fun Context.toString(@StringRes stringRes: Int): String {
    return getString(stringRes)
}