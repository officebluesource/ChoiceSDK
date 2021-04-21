package at.bluesource.choicesdk.maps.gms

import android.os.Parcel
import at.bluesource.choicesdk.maps.common.BitmapDescriptor
import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.options.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/**
 * Wrapper class for gms version of MarkerOptions
 *
 * @property markerOptions gms MarkerOptions instance
 * @see com.google.android.gms.maps.model.MarkerOptions
 */
internal class GmsMarkerOptions(private val markerOptions: com.google.android.gms.maps.model.MarkerOptions) :
    MarkerOptions {

    override fun alpha(alpha: Float): MarkerOptions {
        markerOptions.alpha(alpha)
        return this
    }

    override fun anchor(u: Float, v: Float): MarkerOptions {
        markerOptions.anchor(u, v)
        return this
    }

    override fun draggable(draggable: Boolean): MarkerOptions {
        markerOptions.draggable(draggable)
        return this
    }

    override fun flat(flat: Boolean): MarkerOptions {
        markerOptions.flat(flat)
        return this
    }

    override fun getAnchorU(): Float {
        return markerOptions.anchorU
    }

    override fun getAnchorV(): Float {
        return markerOptions.anchorV
    }

    override fun getInfoWindowAnchorU(): Float {
        return markerOptions.infoWindowAnchorU
    }

    override fun getInfoWindowAnchorV(): Float {
        return markerOptions.infoWindowAnchorV
    }

    override fun getPosition(): LatLng {
        return markerOptions.position.toChoiceLatLng()
    }

    override fun getRotation(): Float {
        return markerOptions.rotation
    }

    override fun getSnippet(): String {
        return markerOptions.snippet
    }

    override fun getTitle(): String {
        return markerOptions.title
    }

    override fun getZIndex(): Float {
        return markerOptions.zIndex
    }

    override fun icon(bitmapDescriptor: BitmapDescriptor): MarkerOptions {
        when (bitmapDescriptor) {
            is BitmapDescriptor.GmsBitmapDescriptor -> markerOptions.icon(bitmapDescriptor.value)
            else -> markerOptions.icon(BitmapDescriptorFactory.defaultMarker())
        }
        return this
    }

    override fun defaultIcon(): MarkerOptions {
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker())
        return this
    }

    override fun infoWindowAnchor(u: Float, v: Float): MarkerOptions {
        markerOptions.infoWindowAnchor(u, v)
        return this
    }

    override fun isDraggable(): Boolean {
        return markerOptions.isDraggable
    }

    override fun isFlat(): Boolean {
        return markerOptions.isFlat
    }

    override fun isVisible(): Boolean {
        return markerOptions.isVisible
    }

    override fun position(latLng: LatLng): MarkerOptions {
        markerOptions.position(latLng.toGmsLatLng())
        return this
    }

    override fun rotation(rotation: Float): MarkerOptions {
        markerOptions.rotation(rotation)
        return this
    }

    override fun snippet(snippet: String): MarkerOptions {
        markerOptions.snippet(snippet)
        return this
    }

    override fun title(title: String): MarkerOptions {
        markerOptions.title(title)
        return this
    }

    override fun visible(visible: Boolean): MarkerOptions {
        markerOptions.visible(visible)
        return this
    }

    override fun writeToParcel(out: Parcel, flags: Int): MarkerOptions {
        markerOptions.writeToParcel(out, flags)
        return this
    }

    override fun zIndex(zIndex: Float): MarkerOptions {
        markerOptions.zIndex(zIndex)
        return this
    }

    override fun clusterable(clusterable: Boolean): MarkerOptions {
        // property not available in GMS marker options
        return this
    }

    internal fun getGmsMarkerOptions(): com.google.android.gms.maps.model.MarkerOptions {
        return this.markerOptions
    }
}