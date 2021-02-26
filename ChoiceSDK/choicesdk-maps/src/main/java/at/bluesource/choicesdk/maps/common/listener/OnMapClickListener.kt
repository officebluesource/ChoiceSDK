package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.LatLng


/**
 * Called when the user makes a tap gesture on the map, but only if none of the overlays of the map handled the gesture.
 *
 * Implementations of this method are always invoked on the Android UI thread.
 */
interface OnMapClickListener {
    fun onMapClick(point: LatLng)
}