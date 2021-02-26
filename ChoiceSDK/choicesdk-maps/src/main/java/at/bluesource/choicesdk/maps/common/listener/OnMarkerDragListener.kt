package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.Marker

/**
 * Callback interface for drag events on a [Marker].
 *
 * Listeners will be invoked on the Android UI thread.
 */
interface OnMarkerDragListener {
    /**
     * Called repeatedly while a marker is being dragged. The marker's location can be accessed via getPosition().
     */
    fun onMarkerDrag(marker: Marker)

    /**
     * Called when a marker has finished being dragged. The marker's location can be accessed via getPosition().
     */
    fun onMarkerDragEnd(marker: Marker)

    /**
     * Called when a marker starts being dragged. The marker's location can be accessed via getPosition();
     * this position may be different to the position prior to the start of the drag because the marker is popped up above the touch point.
     */
    fun onMarkerDragStart(marker: Marker)
}