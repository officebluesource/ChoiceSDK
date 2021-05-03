package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.PointOfInterest

/**
 * Called when a [PointOfInterest] is clicked or tapped.
 *
 * This is called on the Android UI thread.
 */
fun interface OnPoiClickListener {
    fun onPoiClick(poi: PointOfInterest)
}