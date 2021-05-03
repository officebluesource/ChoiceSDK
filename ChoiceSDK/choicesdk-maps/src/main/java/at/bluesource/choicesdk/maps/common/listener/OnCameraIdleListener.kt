package at.bluesource.choicesdk.maps.common.listener

/**
 * Called when camera movement has ended, there are no pending animations and the user has stopped interacting with the map.
 *
 * This is called on the Android UI thread.
 */
fun interface OnCameraIdleListener {
    fun onCameraIdle()
}