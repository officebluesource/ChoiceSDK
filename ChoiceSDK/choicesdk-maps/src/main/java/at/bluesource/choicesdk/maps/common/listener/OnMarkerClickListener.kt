package at.bluesource.choicesdk.maps.common.listener

import at.bluesource.choicesdk.maps.common.Marker


/**
 * Called when a [Marker] has been clicked or tapped.
 *
 * Note: the first thing that happens when a marker is clicked or tapped is that any currently
 * showing info window is closed, and the Map.OnInfoWindowCloseListener is triggered.
 * Then the OnMarkerClickListener is triggered. Therefore, calling isInfoWindowShown()
 * on any marker from the OnMarkerClickListener will return false.
 */
interface OnMarkerClickListener {
    fun onMarkerClick(marker: Marker): Boolean
}