package at.bluesource.choicesdk.maps.common

import android.content.Context
import android.graphics.Bitmap

/**
 * Common interface for hms and gms BitmapDescriptorFactory
 *
 * A factory containing methods for creating [BitmapDescriptor] objects, used for marker icons and ground overlays.
 *
 * @see com.google.android.gms.maps.model.BitmapDescriptorFactory
 * @see com.huawei.hms.maps.model.BitmapDescriptorFactory
 */
interface BitmapDescriptorFactory {

    /**
     * Creates a [BitmapDescriptor] that refers to a colorization of the default marker image.
     *
     * @param hue The hue of the marker, value must be >= 0 && < 360
     * @return the [BitmapDescriptor] that was loaded from the asset or null if failed to load.
     */
    fun defaultMarker(hue: Float): BitmapDescriptor

    /**
     * Creates a [BitmapDescriptor] that refers to the default marker image.
     */
    fun defaultMarker(): BitmapDescriptor

    /**
     * Creates a [BitmapDescriptor] using the name of a Bitmap image in the assets directory.
     *
     * @param assetName The name of a Bitmap image in the assets directory.
     * @return the [BitmapDescriptor] that was loaded from the asset or null if failed to load.
     */
    fun fromAsset(assetName: String): BitmapDescriptor

    /**
     * Creates a [BitmapDescriptor] from a given Bitmap image.
     */
    fun fromBitmap(bitmap: Bitmap): BitmapDescriptor

    /**
     * Creates a [BitmapDescriptor] using the name of a Bitmap image file located in the internal storage.
     *
     * @param fileName the [BitmapDescriptor] that was loaded from the asset or null if failed to load.
     * @return the [BitmapDescriptor] that was loaded from the asset or null if failed to load.
     */
    fun fromFile(fileName: String): BitmapDescriptor

    /**
     * Creates a [BitmapDescriptor] from the absolute file path of a Bitmap image.
     *
     * @param absolutePath The absolute path of the Bitmap image.
     * @return the [BitmapDescriptor] that was loaded from the asset or null if failed to load.
     */
    fun fromPath(absolutePath: String): BitmapDescriptor

    /**
     * Creates a [BitmapDescriptor] using the resource ID of a Bitmap image.
     *
     * @param resourceId The resource ID of a Bitmap image.
     * @return the [BitmapDescriptor] that was loaded from the asset or null if failed to load.
     */
    fun fromResource(resourceId: Int): BitmapDescriptor

    /**
     * Creates a [BitmapDescriptor] using the current context and the resource id of a vector
     *
     * @param context the current context
     * @param vectorResId the resource ID of a vector image.
     * @return the [BitmapDescriptor] that was loaded from the asset or null if failed to load.
     */
    fun fromVector(context: Context, vectorResId: Int): BitmapDescriptor?

    companion object Provider {
        /**
         * Values from
         * gms: https://developers.google.com/android/reference/com/google/android/gms/maps/model/BitmapDescriptorFactory#constants
         * hms: https://developer.huawei.com/consumer/en/doc/HMSCore-References-V5/constant-values-0000001050437107-V5
         */
        const val HUE_AZURE: Float = 210f
        const val HUE_BLUE: Float = 240f
        const val HUE_CYAN: Float = 180f
        const val HUE_GREEN: Float = 120f
        const val HUE_MAGENTA: Float = 300f
        const val HUE_ORANGE: Float = 30f
        const val HUE_RED: Float = 0f
        const val HUE_ROSE: Float = 330f
        const val HUE_VIOLET: Float = 270f
        const val HUE_YELLOW: Float = 60f

        @Throws(UnsupportedOperationException::class)
        fun instance(): BitmapDescriptorFactory {
            return at.bluesource.choicesdk.maps.factory.BitmapDescriptorFactory.getBitmapDescriptorFactory()
        }
    }
}