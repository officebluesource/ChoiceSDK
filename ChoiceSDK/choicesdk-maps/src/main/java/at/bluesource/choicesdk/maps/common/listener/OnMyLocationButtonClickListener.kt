package at.bluesource.choicesdk.maps.common.listener

/**
 *
 * Called when the my location button is clicked.
 * This is called on the Android UI thread.
 *
 * Use [at.bluesource.choicesdk.location.common.FusedLocationProviderClient] if you need to obtain the user's current location.
 *
 * Returns
 *
 *      true if the listener has consumed the event (i.e., the default behavior should not occur)
 *
 *      false otherwise (i.e., the default behavior should occur).
 *
 *      the default behavior is for the camera move such that it is centered on the user location.
 */
interface OnMyLocationButtonClickListener {
    fun onMyLocationButtonClick(): Boolean
}