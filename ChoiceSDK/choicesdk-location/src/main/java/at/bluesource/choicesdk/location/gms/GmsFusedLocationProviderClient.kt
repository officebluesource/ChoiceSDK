package at.bluesource.choicesdk.location.gms

import android.app.PendingIntent
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresPermission
import at.bluesource.choicesdk.core.Outcome
import at.bluesource.choicesdk.core.task.Continuation
import at.bluesource.choicesdk.core.task.GmsTask
import at.bluesource.choicesdk.core.task.Task
import at.bluesource.choicesdk.core.task.listener.OnFailureListener
import at.bluesource.choicesdk.location.common.*
import at.bluesource.choicesdk.location.common.LocationAvailability.Companion.toChoiceLocationAvailability
import at.bluesource.choicesdk.location.common.LocationCallback.Companion.toGmsLocationCallback
import at.bluesource.choicesdk.location.common.LocationRequest.Companion.toGmsLocationRequest
import at.bluesource.choicesdk.location.continuation.ContinuationIdentity
import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Observable

/**
 * Wrapper class for rerouting to gms task using the gms version of FusedLocationProviderClient
 *
 * @property fusedLocationProviderClient gms FusedLocationProviderClient instance
 * @see at.bluesource.choicesdk.core.task.GmsTask
 * @see com.google.android.gms.location.FusedLocationProviderClient
 */
internal class GmsFusedLocationProviderClient(private val fusedLocationProviderClient: com.google.android.gms.location.FusedLocationProviderClient) :
    FusedLocationProviderClient {

    private val locationCallbacks: MutableMap<LocationCallback, com.google.android.gms.location.LocationCallback> =
        mutableMapOf()
    private val locationRelay: PublishRelay<Outcome<LocationResult>> = PublishRelay.create()

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun observeLocation(locationRequest: LocationRequest): Observable<Outcome<LocationResult>> {
        val locationCallback = object : LocationCallback {
            override fun onLocationAvailability(locationAvailability: LocationAvailability?) {
                locationAvailability?.let {
                    if (!it.isLocationAvailable) {
                        locationRelay.accept(
                            Outcome.Failure(
                                UnsupportedOperationException(),
                                "Location not available"
                            ) as Outcome<LocationResult>
                        )
                    }
                }
            }

            override fun onLocationResult(result: LocationResult?) {
                result?.let {
                    locationRelay.accept(Outcome.Success(result) as Outcome<LocationResult>)
                }
            }
        }

        requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        ).continueWith(ContinuationIdentity())
            .addOnFailureListener(object : OnFailureListener {
                override fun onFailure(e: Exception) {
                    locationRelay.accept(Outcome.Failure(e) as Outcome<LocationResult>)
                }
            })
        return locationRelay
    }

    override fun stopLocationUpdates() {
        locationCallbacks.entries.forEach { entry ->
            removeGmsLocationUpdates(entry.value)
        }

        locationCallbacks.clear()
    }

    override fun flushLocations(): Task<Void?> {
        return GmsTask(
            fusedLocationProviderClient.flushLocations()
        ).continueWith(
            ContinuationIdentity()
        )
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun getLastLocation(): Task<Location?> {
        return GmsTask(
            fusedLocationProviderClient.lastLocation
        ).continueWith(
            ContinuationIdentity<Location?>()
        )
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun getLocationAvailability(): Task<LocationAvailability?> {
        return GmsTask(
            fusedLocationProviderClient.locationAvailability
        ).continueWith(object :
            Continuation<com.google.android.gms.location.LocationAvailability?, LocationAvailability?> {
            @Throws(Exception::class)
            override fun then(result: Task<com.google.android.gms.location.LocationAvailability?>): LocationAvailability? {
                return result.getResult()?.toChoiceLocationAvailability()
            }
        }).continueWith(ContinuationIdentity<LocationAvailability?>())
    }

    override fun removeLocationUpdates(callbackIntent: PendingIntent): Task<Void?> {
        return GmsTask(
            fusedLocationProviderClient.removeLocationUpdates(callbackIntent)
        ).continueWith(ContinuationIdentity())
    }

    override fun removeLocationUpdates(callback: LocationCallback): Task<Void?> {
        val gmsCallback = locationCallbacks[callback]
        locationCallbacks.remove(callback)

        return GmsTask(
            fusedLocationProviderClient.removeLocationUpdates(gmsCallback)
        ).continueWith(ContinuationIdentity())
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun requestLocationUpdates(
        request: LocationRequest,
        callback: LocationCallback,
        looper: Looper
    ): Task<Void?> {
        val gmsLocationCallback = mapCheck(callback)

        return GmsTask(
            fusedLocationProviderClient.requestLocationUpdates(
                request.toGmsLocationRequest(),
                gmsLocationCallback,
                looper
            )
        ).continueWith(ContinuationIdentity())
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun requestLocationUpdates(
        request: LocationRequest,
        callbackIntent: PendingIntent
    ): Task<Void?> {
        return GmsTask(
            fusedLocationProviderClient.requestLocationUpdates(
                request.toGmsLocationRequest(),
                callbackIntent
            )
        ).continueWith(ContinuationIdentity())
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun setMockLocation(mockLocation: Location): Task<Void?> {
        return GmsTask(
            fusedLocationProviderClient.setMockLocation(mockLocation)
        ).continueWith(ContinuationIdentity())
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun setMockMode(isMockMode: Boolean): Task<Void?> {
        return GmsTask(
            fusedLocationProviderClient.setMockMode(isMockMode)
        ).continueWith(ContinuationIdentity())
    }

    private fun removeGmsLocationUpdates(callback: com.google.android.gms.location.LocationCallback) {
        try {
            fusedLocationProviderClient.removeLocationUpdates(callback)
        } catch (e: Exception) {
            Log.e("ChoiceSdk", "${e.message}")
        }
    }

    private fun mapCheck(callback: LocationCallback): com.google.android.gms.location.LocationCallback {
        val gmsCallback = callback.toGmsLocationCallback()

        if (locationCallbacks.containsKey(callback)) {
            locationCallbacks[callback]?.let {
                removeGmsLocationUpdates(it)
            }
        }
        locationCallbacks[callback] = gmsCallback
        return gmsCallback
    }
}