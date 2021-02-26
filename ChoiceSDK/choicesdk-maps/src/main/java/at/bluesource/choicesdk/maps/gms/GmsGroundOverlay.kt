package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.GroundOverlay
import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toChoiceLatLngBounds
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toGmsLatLngBounds

/**
 * Wrapper class for gms version of GroundOverlay
 *
 * @property groundOverlay gms groundOverlay instance
 * @see com.google.android.gms.maps.model.GroundOverlay
 */
internal class GmsGroundOverlay(private val groundOverlay: com.google.android.gms.maps.model.GroundOverlay) :
    GroundOverlay {
    override var bearing: Float
        get() = groundOverlay.bearing
        set(value) {
            groundOverlay.bearing = value
        }

    override var bounds: LatLngBounds
        get() = groundOverlay.bounds.toChoiceLatLngBounds()
        set(value) {
            groundOverlay.setPositionFromBounds(value.toGmsLatLngBounds())
        }

    override val height: Float
        get() = groundOverlay.height

    override val id: String
        get() = groundOverlay.id

    override var position: LatLng
        get() = groundOverlay.position.toChoiceLatLng()
        set(value) {
            groundOverlay.position = value.toGmsLatLng()
        }

    override var tag: Any?
        get() = groundOverlay.tag
        set(value) {
            groundOverlay.tag = value
        }

    override var transparency: Float
        get() = groundOverlay.transparency
        set(value) {
            groundOverlay.transparency = value
        }

    override val width: Float
        get() = groundOverlay.width

    override var zIndex: Float
        get() = groundOverlay.zIndex
        set(value) {
            groundOverlay.zIndex = value
        }

    override val hashCode: Int
        get() = groundOverlay.hashCode()

    override var isClickable: Boolean
        get() = groundOverlay.isClickable
        set(value) {
            groundOverlay.isClickable = value
        }

    override var isVisible: Boolean
        get() = groundOverlay.isVisible
        set(value) {
            groundOverlay.isVisible = value
        }

    override fun remove() {
        groundOverlay.remove()
    }

    override fun setDimensions(dimensions: Float) {
        groundOverlay.setDimensions(dimensions)
    }

    override fun setDimensions(width: Float, height: Float) {
        groundOverlay.setDimensions(width, height)
    }

    override fun setPositionFromBounds(bounds: LatLngBounds) {
        groundOverlay.setPositionFromBounds(bounds.toGmsLatLngBounds())
    }

    internal fun getGroundOverlay(): com.google.android.gms.maps.model.GroundOverlay {
        return this.groundOverlay
    }
}