package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.Marker

/**
 * Called when the [Marker]'s info window is closed.
 *
 * This is called on the Android UI thread.
 */
fun interface OnInfoWindowCloseListener {
    fun onInfoWindowClose(marker: Marker)
}