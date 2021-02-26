package at.bluesource.choicesdk.maps.common.options

import android.os.Parcel
import at.bluesource.choicesdk.maps.common.BitmapDescriptor
import at.bluesource.choicesdk.maps.common.GroundOverlay
import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds
import at.bluesource.choicesdk.maps.factory.GroundOverlayOptionsFactory
import at.bluesource.choicesdk.maps.gms.GmsGroundOverlayOptions
import at.bluesource.choicesdk.maps.hms.HmsGroundOverlayOptions

/**
 * Common interface for hms and gms MarkerOptions
 *
 * Defines options for a [GroundOverlay].
 *
 * @see com.google.android.gms.maps.model.GroundOverlayOptions
 * @see com.huawei.hms.maps.model.GroundOverlayOptions
 */
interface GroundOverlayOptions {

    fun anchor(u: Float, v: Float): GroundOverlayOptions
    fun bearing(bearing: Float): GroundOverlayOptions
    fun clickable(clickable: Boolean): GroundOverlayOptions
    fun getAnchorU(): Float
    fun getAnchorV(): Float
    fun getBearing(): Float
    fun getBounds(): LatLngBounds
    fun getHeight(): Float
    fun getImage(): BitmapDescriptor
    fun getLocation(): LatLng
    fun getTransparency(): Float
    fun getWidth(): Float
    fun getZIndex(): Float
    fun image(bitmapDescriptor: BitmapDescriptor): GroundOverlayOptions
    fun isClickable(): Boolean
    fun isVisible(): Boolean
    fun position(location: LatLng, width: Float): GroundOverlayOptions
    fun position(location: LatLng, width: Float, height: Float): GroundOverlayOptions
    fun positionFromBounds(bounds: LatLngBounds): GroundOverlayOptions

    /**
     * Specifies the transparency of the ground overlay.
     * @param transparency A float in the range [0..1] where 0 means that the ground overlay is opaque and 1 means that the ground overlay is transparent.
     * @return
     */
    fun transparency(transparency: Float): GroundOverlayOptions
    fun visible(visible: Boolean): GroundOverlayOptions
    fun writeToParcel(out: Parcel, flags: Int)
    fun zIndex(zIndex: Float): GroundOverlayOptions

    companion object {

        fun create(): GroundOverlayOptions {
            return GroundOverlayOptionsFactory.getGroundOverlayOptions()
        }

        internal fun GroundOverlayOptions.toGmsGroundOverlayOptions(): com.google.android.gms.maps.model.GroundOverlayOptions? {
            return when (this) {
                is GmsGroundOverlayOptions -> {
                    this.getGroundOverlayOptions()
                }
                else -> null
            }
        }

        internal fun GroundOverlayOptions.toHmsGroundOverlayOptions(): com.huawei.hms.maps.model.GroundOverlayOptions? {
            return when (this) {
                is HmsGroundOverlayOptions -> {
                    this.getGroundOverlayOptions()
                }
                else -> null
            }
        }
    }
}