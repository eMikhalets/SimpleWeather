package com.emikhalets.simpleweather.utils.extensions

import android.location.Location

// TODO: hide api
val tempApiKey = "ccfcbe4877b2425ea06153509221804"

val Location.coords: String
    get() = "$latitude, $longitude"