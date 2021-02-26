package at.bluesource.choicesdk.maps.common.listener

/**
 * Called when the developer explicitly calls the stopAnimation() method or if the reason
 * for camera motion has changed before the onCameraIdle had a chance to fire after
 * the previous animation. Do not update or animate the camera from within this method.
 *
 * This is called on the Android UI thread.
 */
interface OnCameraMoveCanceledListener {
    fun onCameraMoveCanceled()
}