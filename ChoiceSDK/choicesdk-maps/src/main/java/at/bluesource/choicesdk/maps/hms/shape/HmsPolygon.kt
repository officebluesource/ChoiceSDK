package at.bluesource.choicesdk.maps.hms.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.PatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toChoice
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toHmsPatternItem
import at.bluesource.choicesdk.maps.common.shape.Polygon

/**
 * Wrapper class for hms polygon version
 *
 * @property polygon hms Polygon instance
 * @see com.huawei.hms.maps.model.Polygon
 */
internal class HmsPolygon(private var polygon: com.huawei.hms.maps.model.Polygon) : Polygon {

    override val id: String
        get() = polygon.id

    override var points: List<LatLng>
        get() = polygon.points.map { it.toChoiceLatLng() }
        set(value) {
            polygon.points = value.map { it.toHmsLatLng() }
        }

    override var holes: List<List<LatLng>>
        get() = polygon.holes.map { list -> list.map { it.toChoiceLatLng() } }
        set(value) {
            polygon.holes = value.map { it.map { latLng -> latLng.toHmsLatLng() } }
        }

    override var fillColor: Int
        get() = polygon.fillColor
        set(value) {
            polygon.fillColor = value
        }

    override var geodesic: Boolean
        get() = polygon.isGeodesic
        set(value) {
            polygon.isGeodesic = value
        }

    override var strokeJointType: Int
        get() = polygon.strokeJointType
        set(value) {
            polygon.strokeJointType = value
        }

    override var strokePattern: List<PatternItem>
        get() = polygon.strokePattern.orEmpty().map { it.toChoice() }
        set(value) {
            polygon.strokePattern = value.map { it.toHmsPatternItem() }
        }

    override var clickable: Boolean
        get() = polygon.isClickable
        set(value) {
            polygon.isClickable = value
        }

    override var strokeColor: Int
        get() = polygon.strokeColor
        set(value) {
            polygon.strokeColor = value
        }

    override var strokeWidth: Float
        get() = polygon.strokeWidth
        set(value) {
            polygon.strokeWidth = value
        }

    override var visible: Boolean
        get() = polygon.isVisible
        set(value) {
            polygon.isVisible = value
        }

    override var zIndex: Float
        get() = polygon.zIndex
        set(value) {
            polygon.zIndex = value
        }

    override var tag: Any?
        get() = polygon.tag
        set(value) {
            polygon.tag = value
        }

    override fun remove() {
        polygon.remove()
    }
}
