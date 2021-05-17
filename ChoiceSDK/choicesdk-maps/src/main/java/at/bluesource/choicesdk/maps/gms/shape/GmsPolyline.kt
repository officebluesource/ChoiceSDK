package at.bluesource.choicesdk.maps.gms.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.PatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toChoice
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toGmsPatternItem
import at.bluesource.choicesdk.maps.common.shape.Cap
import at.bluesource.choicesdk.maps.common.shape.Cap.Companion.toChoiceCap
import at.bluesource.choicesdk.maps.common.shape.Cap.Companion.toGmsCap
import at.bluesource.choicesdk.maps.common.shape.Polyline

/**
 * Wrapper class for gms polyline version
 *
 * @property polyline gms Polyline instance
 * @see com.google.android.gms.maps.model.Polyline
 */
internal class GmsPolyline(private val polyline: com.google.android.gms.maps.model.Polyline) : Polyline {

    override val id: String
        get() = polyline.id

    override var points: List<LatLng>
        get() = polyline.points.map { it.toChoiceLatLng() }
        set(value) {
            polyline.points = value.map { it.toGmsLatLng() }
        }

    override var geodesic: Boolean
        get() = polyline.isGeodesic
        set(value) {
            polyline.isGeodesic = value
        }

    override var jointType: Int
        get() = polyline.jointType
        set(value) {
            polyline.jointType = value
        }

    override var pattern: List<PatternItem>
        get() = polyline.pattern.orEmpty().map { it.toChoice() }
        set(value) {
            polyline.pattern = value.map { it.toGmsPatternItem() }
        }

    override var startCap: Cap
        get() = polyline.startCap.toChoiceCap()
        set(value) {
            polyline.startCap = value.toGmsCap()
        }

    override var endCap: Cap
        get() = polyline.endCap.toChoiceCap()
        set(value) {
            polyline.endCap = value.toGmsCap()
        }

    override var clickable: Boolean
        get() = polyline.isClickable
        set(value) {
            polyline.isClickable = value
        }

    override var color: Int
        get() = polyline.color
        set(value) {
            polyline.color = value
        }

    override var width: Float
        get() = polyline.width
        set(value) {
            polyline.width = value
        }

    override var visible: Boolean
        get() = polyline.isVisible
        set(value) {
            polyline.isVisible = value
        }

    override var zIndex: Float
        get() = polyline.zIndex
        set(value) {
            polyline.zIndex = value
        }

    override var tag: Any?
        get() = polyline.tag
        set(value) {
            polyline.tag = value
        }

    override fun remove() {
        polyline.remove()
    }
}