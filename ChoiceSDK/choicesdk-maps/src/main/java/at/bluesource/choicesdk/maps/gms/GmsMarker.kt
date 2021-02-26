package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.BitmapDescriptor
import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.Marker
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/**
 * Wrapper class for gms version of Marker
 *
 * @property marker gms Marker instance
 * @see com.google.android.gms.maps.model.Marker
 */
internal class GmsMarker(private val marker: com.google.android.gms.maps.model.Marker) : Marker {
    override val id: String
        get() = marker.id
    override var position: LatLng
        get() = marker.position.toChoiceLatLng()
        set(value) {
            marker.position = value.toGmsLatLng()
        }
    override var zIndex: Float
        get() = marker.zIndex
        set(value) {
            marker.zIndex = value
        }
    override var title: String?
        get() = marker.title
        set(value) {
            marker.title = value
        }
    override var snippet: String?
        get() = marker.snippet
        set(value) {
            marker.snippet = value
        }
    override var draggable: Boolean
        get() = marker.isDraggable
        set(value) {
            marker.isDraggable = value
        }
    override val infoWindowShown: Boolean
        get() = marker.isInfoWindowShown

    override var visible: Boolean
        get() = marker.isVisible
        set(value) {
            marker.isVisible = value
        }
    override var flat: Boolean
        get() = marker.isFlat
        set(value) {
            marker.isFlat = value
        }

    override var rotation: Float
        get() = marker.rotation
        set(value) {
            marker.rotation = value
        }

    override var alpha: Float
        get() = marker.alpha
        set(value) {
            marker.alpha = value
        }

    override var tag: Any?
        get() = marker.tag
        set(value) {
            marker.tag = value
        }

    override fun remove() {
        marker.remove()
    }

    override fun setIcon(bitmapDescriptor: BitmapDescriptor) {
        when (bitmapDescriptor) {
            is BitmapDescriptor.GmsBitmapDescriptor -> marker.setIcon(bitmapDescriptor.value)
            else -> marker.setIcon(BitmapDescriptorFactory.defaultMarker())
        }
    }

    override fun setAnchor(anchorU: Float, anchorV: Float) {
        marker.setAnchor(anchorU, anchorV)
    }

    override fun setInfoWindowAnchor(anchorU: Float, anchorV: Float) {
        marker.setInfoWindowAnchor(anchorU, anchorV)
    }

    override fun showInfoWindow() {
        marker.showInfoWindow()
    }

    override fun hideInfoWindow() {
        marker.hideInfoWindow()
    }

    override fun isInfoWindowShown(): Boolean {
        return marker.isInfoWindowShown
    }

    override fun equals(other: Any?): Boolean {
        return marker == other
    }

    override fun hashCode(): Int {
        return marker.hashCode()
    }
}