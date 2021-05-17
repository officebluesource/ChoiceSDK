package at.bluesource.choicesdk.maps.gms.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.PatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toChoice
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toGmsPatternItem
import at.bluesource.choicesdk.maps.common.shape.Polygon

/**
 * Wrapper class for gms polygon version
 *
 * @property polygon gms Polyline instance
 * @see com.google.android.gms.maps.model.Polygon
 */
internal class GmsPolygon(private var polygon: com.google.android.gms.maps.model.Polygon) : Polygon {

    override val id: String
        get() = polygon.id

    override var points: List<LatLng>
        get() = polygon.points.map { it.toChoiceLatLng() }
        set(value) {
            polygon.points = value.map { it.toGmsLatLng() }
        }

    override var holes: List<List<LatLng>>
        get() = polygon.holes.map { list -> list.map { it.toChoiceLatLng() } }
        set(value) {
            polygon.holes = value.map { it.map { latLng -> latLng.toGmsLatLng() } }
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
            polygon.strokePattern = value.map { it.toGmsPatternItem() }
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
