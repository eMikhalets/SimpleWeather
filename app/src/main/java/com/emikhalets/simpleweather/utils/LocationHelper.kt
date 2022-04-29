package com.emikhalets.simpleweather.utils

import android.content.Context
import android.content.IntentSender
import android.location.Location
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.tasks.CancellationTokenSource
import timber.log.Timber

class LocationHelper(context: Context) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fun getCurrentLocation(
        onSuccess: (Location) -> Unit,
        onFailure: () -> Unit
    ) {
        try {
            fusedLocationClient.getCurrentLocation(
                LocationRequest.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            ).addOnSuccessListener { location ->
                location?.let { onSuccess(location) } ?: onFailure()
            }.addOnFailureListener { exception ->
                Timber.e(exception)
                onFailure()
            }
        } catch (ex: SecurityException) {
            Timber.e(ex)
        }
    }

    companion object {
        fun checkLocationSettings(
            context: Context,
            onDisabled: (IntentSenderRequest) -> Unit,
            onEnabled: () -> Unit
        ) {
            val locationRequest = LocationSettingsRequest.Builder()
                .addLocationRequest(LocationRequest.create())
            LocationServices.getSettingsClient(context)
                .checkLocationSettings(locationRequest.build())
                .addOnSuccessListener { onEnabled() }
                .addOnFailureListener { exception ->
                    if (exception is ResolvableApiException) {
                        try {
                            val intentSenderRequest = IntentSenderRequest
                                .Builder(exception.resolution)
                                .build()
                            onDisabled(intentSenderRequest)
                        } catch (sendEx: IntentSender.SendIntentException) {
                            // ignore here
                        }
                    }
                }
        }
    }
}