package com.emikhalets.simpleweather.utils

import android.Manifest
import android.app.Activity.RESULT_OK
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun locationPermission(
    onEnabled: () -> Unit,
    onDisabled: () -> Unit,
) = rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
    val fineLocation = it[Manifest.permission.ACCESS_FINE_LOCATION] ?: false
    val coarseLocation = it[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false

    if (fineLocation && coarseLocation) onEnabled()
    else onDisabled()
}

fun ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>.requestLocationPermissions() {
    this.launch(
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
}

@Composable
fun locationSettings(
    onEnabled: () -> Unit,
    onDisabled: () -> Unit,
) = rememberLauncherForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
    if (it.resultCode == RESULT_OK) onEnabled()
    else onDisabled()

}

fun ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>.requestLocationSettings(
    intent: IntentSenderRequest
) {
    this.launch(intent)
}