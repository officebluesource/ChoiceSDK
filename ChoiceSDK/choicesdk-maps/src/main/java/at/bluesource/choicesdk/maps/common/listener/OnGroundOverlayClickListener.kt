package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.GroundOverlay

/**
 * Called when a [GroundOverlay] is clicked.
 *
 * This is called on the Android UI thread.
 */
interface OnGroundOverlayClickListener {
    fun onGroundOverlayClickListener(groundOverlay: GroundOverlay)
}