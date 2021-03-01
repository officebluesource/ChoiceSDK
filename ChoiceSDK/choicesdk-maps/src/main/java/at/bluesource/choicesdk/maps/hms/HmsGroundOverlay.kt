package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.GroundOverlay
import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toChoiceLatLngBounds
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toHmsLatLngBounds

/**
 * Wrapper class for hms version of GroundOverlay
 *
 * @property groundOverlay gms groundOverlay instance
 * @see com.huawei.hms.maps.model.GroundOverlay
 */
internal class HmsGroundOverlay(private val groundOverlay: com.huawei.hms.maps.model.GroundOverlay) :
        GroundOverlay {
    override var bearing: Float
        get() = groundOverlay.bearing
        set(value) {
            groundOverlay.bearing = value
        }

    override var bounds: LatLngBounds
        get() = groundOverlay.bounds.toChoiceLatLngBounds()
        set(value) {
            groundOverlay.setPositionFromBounds(value.toHmsLatLngBounds())
        }

    override val height: Float
        get() = groundOverlay.height

    override val id: String
        get() = groundOverlay.id

    override var position: LatLng
        get() = groundOverlay.position.toChoiceLatLng()
        set(value) {
            groundOverlay.position = value.toHmsLatLng()
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
        groundOverlay.setPositionFromBounds(bounds.toHmsLatLngBounds())
    }

    internal fun getGroundOverlay(): com.huawei.hms.maps.model.GroundOverlay {
        return this.groundOverlay
    }
}