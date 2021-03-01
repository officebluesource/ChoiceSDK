package at.bluesource.choicesdk.maps.gms.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.shape.Cap
import at.bluesource.choicesdk.maps.common.shape.Cap.Companion.toChoiceCap
import at.bluesource.choicesdk.maps.common.shape.Cap.Companion.toGmsCap
import at.bluesource.choicesdk.maps.common.shape.Polyline

/**
 * Wrapper class for gms polyline version
 *
 * @property polyLine gms Polyline instance
 * @see com.google.android.gms.maps.model.Polyline
 */
internal class GmsPolyline(private val polyLine: com.google.android.gms.maps.model.Polyline) : Polyline {
    override val linePoints: List<LatLng>
        get() = polyLine.points.map { it.toChoiceLatLng() }

    override var geodesic: Boolean
        get() = polyLine.isGeodesic
        set(value) {
            polyLine.isGeodesic = value
        }
    override var startCap: Cap
        get() = polyLine.startCap.toChoiceCap()
        set(value) {
            polyLine.startCap = value.toGmsCap()
        }
    override var endCap: Cap
        get() = polyLine.endCap.toChoiceCap()
        set(value) {
            polyLine.endCap = value.toGmsCap()
        }
    override var clickable: Boolean
        get() = polyLine.isClickable
        set(value) {
            polyLine.isClickable = value
        }
    override var strokeColor: Int
        get() = polyLine.color
        set(value) {
            polyLine.color = value
        }
    override var strokeWidth: Float
        get() = polyLine.width
        set(value) {
            polyLine.width = value
        }
    override var visible: Boolean
        get() = polyLine.isVisible
        set(value) {
            polyLine.isVisible = value
        }
    override var zIndex: Float
        get() = polyLine.zIndex
        set(value) {
            polyLine.zIndex = value
        }
    override var tag: Any?
        get() = polyLine.tag
        set(value) {
            polyLine.tag = value
        }

    override fun remove() {
        polyLine.remove()
    }
}