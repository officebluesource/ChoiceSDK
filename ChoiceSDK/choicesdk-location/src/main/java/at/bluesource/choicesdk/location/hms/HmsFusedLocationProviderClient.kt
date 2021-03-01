package at.bluesource.choicesdk.location.hms

import android.app.PendingIntent
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresPermission
import at.bluesource.choicesdk.core.Outcome
import at.bluesource.choicesdk.core.task.Continuation
import at.bluesource.choicesdk.core.task.HmsTask
import at.bluesource.choicesdk.core.task.Task
import at.bluesource.choicesdk.core.task.listener.OnCompleteListener
import at.bluesource.choicesdk.core.task.listener.OnFailureListener
import at.bluesource.choicesdk.location.common.*
import at.bluesource.choicesdk.location.common.LocationAvailability.Companion.toChoiceLocationAvailability
import at.bluesource.choicesdk.location.common.LocationCallback.Companion.toHmsLocationCallback
import at.bluesource.choicesdk.location.common.LocationRequest.Companion.toHmsLocationRequest
import at.bluesource.choicesdk.location.continuation.ContinuationIdentity
import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Observable

/**
 * Wrapper class for rerouting to hms task using the hms version of FusedLocationProviderClient
 *
 * @property fusedLocationProviderClient hms FusedLocationProviderClient instance
 * @see at.bluesource.choicesdk.core.task.HmsTask
 * @see com.huawei.hms.location.FusedLocationProviderClient
 */
internal class HmsFusedLocationProviderClient(private val fusedLocationProviderClient: com.huawei.hms.location.FusedLocationProviderClient) :
    FusedLocationProviderClient {

    private val locationCallbacks: MutableMap<LocationCallback, com.huawei.hms.location.LocationCallback> =
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
            removeHmsLocationUpdates(entry.value)
        }

        locationCallbacks.clear()
    }

    /**
     * Note from official doc: https://developer.huawei.com/consumer/en/doc/development/HMSCore-References-V5/fusedlocationproviderclient-0000001050746169-V5#EN-US_TOPIC_0000001050746169__section1653772245611
     * Currently, the flushLocations() method is not provided, nor implemented in the SDK.
     * As of date: 06.07.2020
     */
    override fun flushLocations(): Task<Void?> {
        return HmsTask(
            fusedLocationProviderClient.flushLocations()
        ).continueWith(
            ContinuationIdentity()
        )
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun getLastLocation(): Task<Location?> {
        return HmsTask(
            fusedLocationProviderClient.lastLocation
        ).continueWith(
            ContinuationIdentity()
        )
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun getLocationAvailability(): Task<LocationAvailability?> {
        return HmsTask(
            fusedLocationProviderClient.locationAvailability
        ).continueWith(object :
            Continuation<com.huawei.hms.location.LocationAvailability?, LocationAvailability?> {
            @Throws(Exception::class)
            override fun then(result: Task<com.huawei.hms.location.LocationAvailability?>): LocationAvailability? {
                return result.getResult()?.toChoiceLocationAvailability()
            }
        }).continueWith(ContinuationIdentity())
    }

    override fun removeLocationUpdates(callbackIntent: PendingIntent): Task<Void?> {
        return HmsTask(
            fusedLocationProviderClient.removeLocationUpdates(callbackIntent)
        ).continueWith(ContinuationIdentity())
    }

    override fun removeLocationUpdates(callback: LocationCallback): Task<Void?> {
        val hmsCallback = locationCallbacks[callback]
        locationCallbacks.remove(callback)

        return HmsTask(
            fusedLocationProviderClient.removeLocationUpdates(hmsCallback)
        ).continueWith(ContinuationIdentity())
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun requestLocationUpdates(
        request: LocationRequest,
        callback: LocationCallback,
        looper: Looper
    ): Task<Void?> {
        val hmsLocationCallback = mapCheck(callback)

        return HmsTask(
            fusedLocationProviderClient.requestLocationUpdates(
                request.toHmsLocationRequest(),
                hmsLocationCallback,
                looper
            )
        ).continueWith(ContinuationIdentity())
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun requestLocationUpdates(
        request: LocationRequest,
        callbackIntent: PendingIntent
    ): Task<Void?> {
        return HmsTask(
            fusedLocationProviderClient.requestLocationUpdates(
                request.toHmsLocationRequest(),
                callbackIntent
            )
        ).continueWith(ContinuationIdentity())
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun setMockLocation(mockLocation: Location): Task<Void?> {
        return HmsTask(
            fusedLocationProviderClient.setMockLocation(mockLocation)
        ).continueWith(ContinuationIdentity())
    }

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    override fun setMockMode(isMockMode: Boolean): Task<Void?> {
        return HmsTask(
            fusedLocationProviderClient.setMockMode(isMockMode)
        ).continueWith(ContinuationIdentity())
    }

    private fun removeHmsLocationUpdates(callback: com.huawei.hms.location.LocationCallback) {
        try {
            fusedLocationProviderClient.removeLocationUpdates(callback)
        } catch (e: Exception) {
            Log.e("ChoiceSdk", "${e.message}")
        }
    }

    private fun mapCheck(callback: LocationCallback): com.huawei.hms.location.LocationCallback {
        val hmsCallback = callback.toHmsLocationCallback()

        if (locationCallbacks.containsKey(callback)) {
            locationCallbacks[callback]?.let {
                removeHmsLocationUpdates(it)
            }
        }
        locationCallbacks[callback] = hmsCallback
        return hmsCallback
    }
}