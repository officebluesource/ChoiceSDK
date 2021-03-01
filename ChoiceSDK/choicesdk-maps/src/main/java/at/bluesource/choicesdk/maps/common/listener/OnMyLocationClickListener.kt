package at.bluesource.choicesdk.maps.common.listener

import android.location.Location

/**
 * Called when the My Location dot is clicked.
 *
 * This is called on the Android UI thread.
 */
interface OnMyLocationClickListener {
    fun onMyLocationClick(location: Location)
}