package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.Marker

/**
 * Called when the [Marker]'s info window is clicked.
 *
 * This is called on the Android UI thread.
 */
fun interface OnInfoWindowClickListener {
    fun onInfoWindowClick(marker: Marker)
}