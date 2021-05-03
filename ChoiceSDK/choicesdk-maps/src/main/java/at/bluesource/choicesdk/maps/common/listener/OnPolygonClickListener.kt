package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.shape.Polygon

/**
 * Called when a [Polygon] is clicked.
 *
 * This is called on the Android UI thread.
 */
fun interface OnPolygonClickListener {
    fun onPolygonClick(polygon: Polygon)
}