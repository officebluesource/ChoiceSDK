package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.shape.Circle

/**
 * Called when a [Circle] is clicked.
 *
 * This is called on the Android UI thread.
 */
fun interface OnCircleClickListener {
    fun onCircleClick(circle: Circle)
}