package at.bluesource.choicesdk.maps.gms.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.PatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toChoice
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toGmsPatternItem
import at.bluesource.choicesdk.maps.common.shape.Circle

/**
 * Wrapper class for gms circle version
 *
 * @property circle gms circle instance
 * @see com.google.android.gms.maps.model.Circle
 */
internal class GmsCircle(private var circle: com.google.android.gms.maps.model.Circle) : Circle {

    override val id: String
        get() = circle.id

    override var center: LatLng
        get() = circle.center.toChoiceLatLng()
        set(value) {
            circle.center = value.toGmsLatLng()
        }

    override var clickable: Boolean
        get() = circle.isClickable
        set(value) {
            circle.isClickable = value
        }

    override var fillColor: Int
        get() = circle.fillColor
        set(value) {
            circle.fillColor = value
        }

    override var radius: Double
        get() = circle.radius
        set(value) {
            circle.radius = value
        }

    override var strokeColor: Int
        get() = circle.strokeColor
        set(value) {
            circle.strokeColor = value
        }

    override var strokePattern: List<PatternItem>
        get() = circle.strokePattern.orEmpty().map { it.toChoice() }
        set(value) {
            circle.strokePattern = value.map { it.toGmsPatternItem() }
        }

    override var strokeWidth: Float
        get() = circle.strokeWidth
        set(value) {
            circle.strokeWidth = value
        }

    override var visible: Boolean
        get() = circle.isVisible
        set(value) {
            circle.isVisible = value
        }

    override var zIndex: Float
        get() = circle.zIndex
        set(value) {
            circle.zIndex = value
        }

    override var tag: Any?
        get() = circle.tag
        set(value) {
            circle.tag = value
        }

    override fun remove() {
        circle.remove()
    }

}