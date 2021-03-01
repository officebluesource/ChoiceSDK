package at.bluesource.choicesdk.maps.gms

import android.os.Parcel
import at.bluesource.choicesdk.maps.common.BitmapDescriptor
import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toChoiceLatLngBounds
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toGmsLatLngBounds
import at.bluesource.choicesdk.maps.common.options.GroundOverlayOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/**
 * Wrapper class for gms version of GroundOverlayOptions
 *
 * @property groundOverlayOptions gms groundOverlayOptions instance
 * @see com.google.android.gms.maps.model.GroundOverlayOptions
 */
internal class GmsGroundOverlayOptions(private val groundOverlayOptions: com.google.android.gms.maps.model.GroundOverlayOptions) :
    GroundOverlayOptions {
    override fun anchor(u: Float, v: Float): GroundOverlayOptions {
        groundOverlayOptions.anchor(u, v)
        return this
    }

    override fun bearing(bearing: Float): GroundOverlayOptions {
        groundOverlayOptions.bearing(bearing)
        return this
    }

    override fun clickable(clickable: Boolean): GroundOverlayOptions {
        groundOverlayOptions.clickable(clickable)
        return this
    }

    override fun getAnchorU(): Float {
        return groundOverlayOptions.anchorU
    }

    override fun getAnchorV(): Float {
        return groundOverlayOptions.anchorV
    }

    override fun getBearing(): Float {
        return groundOverlayOptions.bearing
    }

    override fun getBounds(): LatLngBounds {
        return groundOverlayOptions.bounds.toChoiceLatLngBounds()
    }

    override fun getHeight(): Float {
        return groundOverlayOptions.height
    }

    override fun getImage(): BitmapDescriptor {
        return BitmapDescriptor.GmsBitmapDescriptor(groundOverlayOptions.image)
    }

    override fun getLocation(): LatLng {
        return groundOverlayOptions.location.toChoiceLatLng()
    }

    override fun getTransparency(): Float {
        return groundOverlayOptions.transparency
    }

    override fun getWidth(): Float {
        return groundOverlayOptions.width
    }

    override fun getZIndex(): Float {
        return groundOverlayOptions.zIndex
    }

    override fun image(bitmapDescriptor: BitmapDescriptor): GroundOverlayOptions {
        when (bitmapDescriptor) {
            is BitmapDescriptor.GmsBitmapDescriptor -> groundOverlayOptions.image(bitmapDescriptor.value)
            else -> groundOverlayOptions.image(BitmapDescriptorFactory.defaultMarker())
        }
        return this
    }

    override fun isClickable(): Boolean {
        return groundOverlayOptions.isClickable
    }

    override fun isVisible(): Boolean {
        return groundOverlayOptions.isVisible
    }

    override fun position(location: LatLng, width: Float): GroundOverlayOptions {
        groundOverlayOptions.position(location.toGmsLatLng(), width)
        return this
    }

    override fun position(location: LatLng, width: Float, height: Float): GroundOverlayOptions {
        groundOverlayOptions.position(location.toGmsLatLng(), width, height)
        return this
    }

    override fun positionFromBounds(bounds: LatLngBounds): GroundOverlayOptions {
        groundOverlayOptions.positionFromBounds(bounds.toGmsLatLngBounds())
        return this
    }

    override fun transparency(transparency: Float): GroundOverlayOptions {
        groundOverlayOptions.transparency(transparency)
        return this
    }

    override fun visible(visible: Boolean): GroundOverlayOptions {
        groundOverlayOptions.visible(visible)
        return this
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        groundOverlayOptions.writeToParcel(out, flags)
    }

    override fun zIndex(zIndex: Float): GroundOverlayOptions {
        groundOverlayOptions.zIndex(zIndex)
        return this
    }

    internal fun getGroundOverlayOptions(): com.google.android.gms.maps.model.GroundOverlayOptions {
        return this.groundOverlayOptions
    }
}