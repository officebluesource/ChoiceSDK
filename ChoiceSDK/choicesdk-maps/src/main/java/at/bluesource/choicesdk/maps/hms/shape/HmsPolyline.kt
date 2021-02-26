package at.bluesource.choicesdk.maps.hms.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.shape.Cap
import at.bluesource.choicesdk.maps.common.shape.Cap.Companion.toChoiceCap
import at.bluesource.choicesdk.maps.common.shape.Cap.Companion.toHmsCap
import at.bluesource.choicesdk.maps.common.shape.Polyline

/**
 * Wrapper class for hms polyline version
 *
 * @property polyLine hms Polyline instance
 * @see com.huawei.hms.maps.model.Polyline
 */
internal class HmsPolyline(private val polyLine: com.huawei.hms.maps.model.Polyline) : Polyline {
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
            polyLine.startCap = value.toHmsCap()
        }
    override var endCap: Cap
        get() = polyLine.endCap.toChoiceCap()
        set(value) {
            polyLine.endCap = value.toHmsCap()
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