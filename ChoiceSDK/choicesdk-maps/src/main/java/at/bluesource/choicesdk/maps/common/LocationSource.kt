package at.bluesource.choicesdk.maps.common

import android.location.Location

/**
 *  Common interface for hms/gms LocationSource
 *
 * Defines an interface for providing location data, typically to a [Map] object.
 * A map object has a built-in location provider for its my-location layer, but it can be replaced with another one that implements this interface.
 *
 * A map object activates its location provider using activate(OnLocationChangedListener).
 * While active (between activate(OnLocationChangedListener) and deactivate()), a location provider
 * should push periodic location updates to the listener registered in activate(OnLocationChangedListener).
 * It is the provider's responsibility to use location services wisely according to the map's lifecycle state.
 * For example, it should only using battery-intensive services (like GPS) occasionally, or only while an activity is in the foreground.
 *
 * @see com.google.android.gms.maps.LocationSource
 * @see com.huawei.hms.maps.LocationSource
 */
interface LocationSource {

    fun activate(listener: OnLocationChangedListener)
    fun deactivate()

    interface OnLocationChangedListener {
        /**
         * Called when a new user location is known.
         */
        fun onLocationChanged(location: Location)
    }
}