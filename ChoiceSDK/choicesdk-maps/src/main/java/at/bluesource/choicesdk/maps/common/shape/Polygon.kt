@file:Suppress("unused")

package at.bluesource.choicesdk.maps.common.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.gms.shape.GmsPolygon
import at.bluesource.choicesdk.maps.hms.shape.HmsPolygon

/**
 * A polygon on the earth's surface. A polygon can be convex or concave, it may span the
 * 180 meridian and it can have holes that are not filled in.
 */
interface Polygon : Shape {

    val linePoints: List<LatLng>
    val lineHoles: List<List<LatLng>>
    var fillColor: Int
    var geodesic: Boolean
    var strokeJointType: Int

    fun remove()

    companion object {
        /**
         * BEVEL 	Flat bevel on the outside of the joint.
         * DEFAULT 	Default: Mitered joint, with fixed pointed extrusion equal to half the stroke width on the outside of the joint.
         * ROUND 	Rounded on the outside of the joint by an arc of radius equal to half the stroke width, centered at the vertex.
         *
         * GMS: https://developers.google.com/maps/documentation/android-sdk/reference/com/google/android/libraries/maps/model/JointType
         * HMS: https://developer.huawei.com/consumer/en/doc/HMSCore-References-V5/constant-values-0000001050437107-V5#EN-US_TOPIC_0000001050437107__section54539193202
         */
        const val BEVEL = 1
        const val DEFAULT = 0
        const val ROUND = 2

        internal fun com.google.android.gms.maps.model.Polygon.toChoicePolygon(): Polygon {
            return GmsPolygon(this@toChoicePolygon)
        }

        internal fun com.huawei.hms.maps.model.Polygon.toChoicePolygon(): Polygon {
            return HmsPolygon(this@toChoicePolygon)
        }
    }
}