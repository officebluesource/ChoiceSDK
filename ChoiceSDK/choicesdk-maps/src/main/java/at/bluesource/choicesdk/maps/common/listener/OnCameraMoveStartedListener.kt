package at.bluesource.choicesdk.maps.common.listener

/**
 * Callback interface for when the camera motion starts.
 *
 * This is called on the Android UI thread.
 */
interface OnCameraMoveStartedListener {
    fun onCameraMoveStarted(reason: Int)

    /**
     * gms: https://developers.google.com/maps/documentation/android-sdk/reference/com/google/android/libraries/maps/GoogleMap.OnCameraMoveStartedListener
     * hms: https://developer.huawei.com/consumer/en/doc/HMSCore-References-V5/constant-values-0000001050437107-V5
     */
    companion object {
        const val REASON_API_ANIMATION = 2
        const val REASON_DEVELOPER_ANIMATION = 3
        const val REASON_GESTURE = 1
    }
}