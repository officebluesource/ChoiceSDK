package at.bluesource.choicesdk.maps.common.listener

import android.graphics.Bitmap

/**
 * Invoked when the snapshot has been taken.
 *
 * This is called on the Android UI thread.
 */
fun interface OnSnapshotReadyCallback {
    fun onSnapShotReady(snapshot: Bitmap?)
}