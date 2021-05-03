package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.Marker

/**
 * Called when the user makes a long-press gesture on the [Marker]'s info window.
 *
 * This is called on the Android UI thread.
 */
fun interface OnInfoWindowLongClickListener {
    fun onInfoWindowLongClick(marker: Marker)
}