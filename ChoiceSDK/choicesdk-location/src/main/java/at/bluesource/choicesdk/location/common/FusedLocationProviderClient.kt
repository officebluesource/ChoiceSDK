package at.bluesource.choicesdk.location.common

import android.app.PendingIntent
import android.location.Location
import android.os.Looper
import androidx.annotation.RequiresPermission
import at.bluesource.choicesdk.core.Outcome
import at.bluesource.choicesdk.core.task.Task
import io.reactivex.rxjava3.core.Observable

/**
 * Common interface for FusedLocationProviderClient wrapper
 *
 * Used to request the current location
 *
 * @see com.google.android.gms.location.FusedLocationProviderClient
 * @see com.huawei.hms.location.FusedLocationProviderClient
 */
interface FusedLocationProviderClient {
    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun observeLocation(locationRequest: LocationRequest): Observable<Outcome<LocationResult>>
    fun stopLocationUpdates()

    fun flushLocations(): Task<Void?>

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun getLastLocation(): Task<Location?>

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun getLocationAvailability(): Task<LocationAvailability?>

    fun removeLocationUpdates(callbackIntent: PendingIntent): Task<Void?>
    fun removeLocationUpdates(callback: LocationCallback): Task<Void?>

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun requestLocationUpdates(
        request: LocationRequest,
        callback: LocationCallback,
        looper: Looper
    ): Task<Void?>

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun requestLocationUpdates(
        request: LocationRequest,
        callbackIntent: PendingIntent
    ): Task<Void?>

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun setMockLocation(mockLocation: Location): Task<Void?>

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun setMockMode(isMockMode: Boolean): Task<Void?>
}