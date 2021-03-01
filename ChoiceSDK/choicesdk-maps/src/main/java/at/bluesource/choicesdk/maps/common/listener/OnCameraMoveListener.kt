package at.bluesource.choicesdk.maps.common.listener

/**
 * Called repeatedly as the camera continues to move after an onCameraMoveStarted call.
 * This may be called as often as once every frame and should not perform expensive operations.
 *
 * This is called on the Android UI thread.
 */
interface OnCameraMoveListener {
    fun onCameraMove()
}