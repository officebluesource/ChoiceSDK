package at.bluesource.choicesdk.location.common

import at.bluesource.choicesdk.location.common.LocationAvailability.Companion.toChoiceLocationAvailability
import at.bluesource.choicesdk.location.common.LocationResult.Companion.toChoiceLocationResult

/**
 * Common interface for LocationCallback
 *
 * Used for receiving notifications from the FusedLocationProviderApi when the device location has changed or can no longer be determined.
 * The methods are called if the LocationCallback has been registered with the location client using the [FusedLocationProviderClient.requestLocationUpdates] method
 *
 * @see com.google.android.gms.location.LocationCallback
 * @see com.huawei.hms.location.LocationCallback
 */
interface LocationCallback {
    fun onLocationAvailability(locationAvailability: LocationAvailability?)
    fun onLocationResult(result: LocationResult?)

    companion object {
        internal fun LocationCallback.toGmsLocationCallback(): com.google.android.gms.location.LocationCallback {
            return object : com.google.android.gms.location.LocationCallback() {
                override fun onLocationResult(result: com.google.android.gms.location.LocationResult?) {
                    this@toGmsLocationCallback.onLocationResult(result?.toChoiceLocationResult())
                }

                override fun onLocationAvailability(locationAvailability: com.google.android.gms.location.LocationAvailability?) {
                    this@toGmsLocationCallback.onLocationAvailability(locationAvailability?.toChoiceLocationAvailability())
                }
            }
        }

        internal fun LocationCallback.toHmsLocationCallback(): com.huawei.hms.location.LocationCallback {
            return object : com.huawei.hms.location.LocationCallback() {
                override fun onLocationResult(result: com.huawei.hms.location.LocationResult?) {
                    this@toHmsLocationCallback.onLocationResult(result?.toChoiceLocationResult())
                }

                override fun onLocationAvailability(locationAvailability: com.huawei.hms.location.LocationAvailability?) {
                    this@toHmsLocationCallback.onLocationAvailability(locationAvailability?.toChoiceLocationAvailability())
                }
            }
        }
    }
}