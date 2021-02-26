package at.bluesource.choicesdk.maps.common

/**
 * Common interface callback for GoogleMap, HuaweiMap.CancelableCallback
 *
 * A callback interface for reporting when a task is complete or canceled
 *
 * @see com.google.android.gms.maps.GoogleMap.CancelableCallback
 * @see com.huawei.hms.maps.HuaweiMap.CancelableCallback
 */
interface CancelableCallback {
    fun onCancel()
    fun onFinish()
}