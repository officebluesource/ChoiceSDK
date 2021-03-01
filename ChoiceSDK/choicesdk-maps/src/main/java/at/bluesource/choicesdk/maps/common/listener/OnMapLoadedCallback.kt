package at.bluesource.choicesdk.maps.common.listener

/**
 * Callback interface for when the map has finished rendering. This occurs after all tiles
 * required to render the Map have been fetched, and all labeling is complete.
 *
 * This event will not fire if the map never loads due to connectivity issues, or if the map
 * is continuously changing and never completes loading due to the user constantly interacting with the map.
 */
interface OnMapLoadedCallback {
    /**
     * Called when the map has finished rendering. This will only be called once.
     * You must request another callback if you want to be notified again.
     *
     * This is called on the Android UI thread.
     */
    fun onMapLoaded()
}