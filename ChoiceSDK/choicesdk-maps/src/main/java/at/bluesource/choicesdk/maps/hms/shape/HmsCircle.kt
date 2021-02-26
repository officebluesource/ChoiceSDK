package at.bluesource.choicesdk.maps.hms.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.shape.Circle

/**
 * Wrapper class for hms circle version
 *
 * @property circle hms Circle instance
 * @see com.huawei.hms.maps.model.Circle
 */
internal class HmsCircle(private var circle: com.huawei.hms.maps.model.Circle) : Circle {
    override var center: LatLng
        get() = circle.center.toChoiceLatLng()
        set(value) {
            circle.center = value.toHmsLatLng()
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
            circle.setTag(value)
        }
}