package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.LatLng


/**
 * Called when the user makes a long-press gesture on the [Map], but only if none of the overlays of the map handled the gesture.
 *
 * Implementations of this method are always invoked on the Android UI thread.
 */
fun interface OnMapLongClickListener {
    fun onMapLongClick(point: LatLng)
}