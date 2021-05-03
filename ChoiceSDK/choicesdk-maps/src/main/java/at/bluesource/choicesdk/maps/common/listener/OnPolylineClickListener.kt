package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.shape.Polyline

/**
 * Called when a [Polyline] is clicked.
 *
 * This is called on the Android UI thread.
 */
fun interface OnPolylineClickListener {
    fun onPolylineClick(polyline: Polyline)
}