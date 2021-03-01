package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.maps.gms.GmsGroundOverlay
import at.bluesource.choicesdk.maps.hms.HmsGroundOverlay

/**
 * Common interface for hms/gms GroundOverlay
 *
 * A ground overlay is an image that is fixed to a map.
 *
 * @see com.google.android.gms.maps.model.GroundOverlay
 * @see com.huawei.hms.maps.model.GroundOverlay
 */
interface GroundOverlay {
    var bearing: Float
    var bounds: LatLngBounds
    val height: Float
    val id: String
    var position: LatLng
    var tag: Any?
    var transparency: Float
    val width: Float
    var zIndex: Float
    val hashCode: Int
    var isClickable: Boolean
    var isVisible: Boolean

    fun remove()
    fun setDimensions(dimensions: Float)
    fun setDimensions(width: Float, height: Float)
    fun setPositionFromBounds(bounds: LatLngBounds)

    companion object {
        internal fun com.google.android.gms.maps.model.GroundOverlay.toChoiceGroundOverlay(): GroundOverlay {
            return GmsGroundOverlay(this)
        }

        internal fun com.huawei.hms.maps.model.GroundOverlay.toChoiceGroundOverlay(): GroundOverlay {
            return HmsGroundOverlay(this)
        }
    }
}