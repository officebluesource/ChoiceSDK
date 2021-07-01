package at.bluesource.choicesdk.maps.common.options

import android.os.Parcel
import at.bluesource.choicesdk.maps.common.BitmapDescriptor
import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.Marker
import at.bluesource.choicesdk.maps.factory.MarkerOptionsFactory
import at.bluesource.choicesdk.maps.gms.GmsMarkerOptions
import at.bluesource.choicesdk.maps.hms.HmsMarkerOptions

/**
 * Common interface for hms and gms MarkerOptions
 *
 * Defines MarkerOptions for a [Marker]
 *
 * @see com.google.android.gms.maps.model.MarkerOptions
 * @see com.huawei.hms.maps.model.MarkerOptions
 */
interface MarkerOptions {

    fun alpha(alpha: Float): MarkerOptions

    /**
     * Default is set to (0.5, 1), so the default position for a marker from both hms and gms is on the
     * same position. See [Companion.create]
     *
     * @param u u-coordinate of the anchor, as a ratio of the image width (in the range [0, 1])
     * @param v v-coordinate of the anchor, as a ratio of the image height (in the range [0, 1])
     * @return MarkerOptions
     */
    fun anchor(u: Float, v: Float): MarkerOptions
    fun draggable(draggable: Boolean): MarkerOptions
    fun flat(flat: Boolean): MarkerOptions
    fun getAnchorU(): Float
    fun getAnchorV(): Float
    fun getInfoWindowAnchorU(): Float
    fun getInfoWindowAnchorV(): Float
    fun getPosition(): LatLng
    fun getRotation(): Float
    fun getSnippet(): String?
    fun getTitle(): String?
    fun getZIndex(): Float
    fun icon(bitmapDescriptor: BitmapDescriptor): MarkerOptions
    fun defaultIcon(): MarkerOptions
    fun infoWindowAnchor(u: Float, v: Float): MarkerOptions
    fun isDraggable(): Boolean
    fun isFlat(): Boolean
    fun isVisible(): Boolean
    fun position(latLng: LatLng): MarkerOptions
    fun rotation(rotation: Float): MarkerOptions
    fun snippet(snippet: String): MarkerOptions
    fun title(title: String): MarkerOptions
    fun visible(visible: Boolean): MarkerOptions
    fun writeToParcel(out: Parcel, flags: Int): MarkerOptions
    fun zIndex(zIndex: Float): MarkerOptions
    fun clusterable(clusterable: Boolean): MarkerOptions

    fun toGmsMarkerOptions(): com.google.android.gms.maps.model.MarkerOptions {
        return when (this) {
            is GmsMarkerOptions -> {
                this.getGmsMarkerOptions()
            }
            else -> throw NotImplementedError("Conversion of class ${this.javaClass} to GMS MarkerOptions not supported/implemented.")
        }
    }

    companion object Factory {

        fun create(): MarkerOptions {
            return MarkerOptionsFactory.getMarkerOptions().anchor(0.5f, 1f)
        }

        @JvmStatic
        fun com.google.android.gms.maps.model.MarkerOptions.toChoice(): MarkerOptions {
            return GmsMarkerOptions(this)
        }


        internal fun MarkerOptions.toHmsMarkerOptions(): com.huawei.hms.maps.model.MarkerOptions? {
            return when (this) {
                is HmsMarkerOptions -> {
                    this.getHmsMarkerOptions()
                }
                else -> null
            }
        }
    }
}