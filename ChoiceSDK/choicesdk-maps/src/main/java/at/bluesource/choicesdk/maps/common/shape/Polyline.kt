@file:Suppress("unused")

package at.bluesource.choicesdk.maps.common.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.PatternItem
import at.bluesource.choicesdk.maps.gms.shape.GmsPolyline
import at.bluesource.choicesdk.maps.hms.shape.HmsPolyline

/**
 * A polyline is a list of points, where line segments are drawn between consecutive points.
 * When a polyline is returned, the custom start and end cap bitmap id's are always -1
 * In order to set a new cap use the designated get/set methods of polyline caps to set a new CustomCap
 */
interface Polyline {
    val id: String
    var clickable: Boolean
    var color: Int
    var endCap: Cap
    var geodesic: Boolean
    var jointType: Int
    var pattern: List<PatternItem>
    var points: List<LatLng>
    var startCap: Cap
    var tag: Any?
    var visible: Boolean
    var width: Float
    var zIndex: Float

    fun remove()

    companion object {

        internal fun com.google.android.gms.maps.model.Polyline.toChoicePolyline(): Polyline {
            return GmsPolyline(this@toChoicePolyline)
        }

        internal fun com.huawei.hms.maps.model.Polyline.toChoicePolyline(): Polyline {
            return HmsPolyline(this@toChoicePolyline)
        }
    }
}