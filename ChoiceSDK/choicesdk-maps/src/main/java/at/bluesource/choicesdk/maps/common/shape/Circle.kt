@file:Suppress("unused")

package at.bluesource.choicesdk.maps.common.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.PatternItem
import at.bluesource.choicesdk.maps.gms.shape.GmsCircle
import at.bluesource.choicesdk.maps.hms.shape.HmsCircle

/**
 * A circle on the earth's surface (spherical cap).
 */
interface Circle {
    val id: String
    var center: LatLng
    var clickable: Boolean
    var fillColor: Int
    var radius: Double
    var strokeColor: Int
    var strokePattern: List<PatternItem>
    var strokeWidth: Float
    var tag: Any?
    var visible: Boolean
    var zIndex: Float

    fun remove()

    companion object {
        internal fun com.google.android.gms.maps.model.Circle.toChoiceCircle(): Circle {
            return GmsCircle(this@toChoiceCircle)
        }

        internal fun com.huawei.hms.maps.model.Circle.toChoiceCircle(): Circle {
            return HmsCircle(this@toChoiceCircle)
        }
    }
}