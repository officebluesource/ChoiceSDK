package at.bluesource.choicesdk.maps.common.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.PatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toChoice
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toGmsPatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toHmsPatternItem
import at.bluesource.choicesdk.maps.common.shape.Cap.Companion.toChoiceCap
import at.bluesource.choicesdk.maps.common.shape.Cap.Companion.toGmsCap
import at.bluesource.choicesdk.maps.common.shape.Cap.Companion.toHmsCap

/**
 * Defines options for a [Polyline].
 * Use this Builder to add a polyline to the map.
 */
class PolylineOptions {

    private var clickable: Boolean = false
    private var color: Int = 0xff000000.toInt() // black, also toInt workaround for compiler issue: https://youtrack.jetbrains.com/issue/KT-4749
    private var endCap: Cap = Cap.ButtCap()
    private var geodesic: Boolean = false
    private var jointType: Int = Polygon.DEFAULT
    private var pattern: List<PatternItem> = listOf()
    private val points: MutableList<LatLng> = mutableListOf()
    private var startCap: Cap = Cap.ButtCap()
    private var visible = true
    private var width: Float = 10f
    private var zIndex: Float = 0f

    fun add(vararg points: LatLng): PolylineOptions {
        this.points.addAll(points)
        return this
    }

    fun add(point: LatLng): PolylineOptions {
        this.points.add(point)
        return this
    }

    fun addAll(points: Iterable<LatLng>): PolylineOptions {
        this.points.addAll(points)
        return this
    }

    fun clickable(clickable: Boolean): PolylineOptions {
        this.clickable = clickable
        return this
    }

    fun color(color: Int): PolylineOptions {
        this.color = color
        return this
    }

    fun endCap(cap: Cap): PolylineOptions {
        this.endCap = cap
        return this
    }

    fun geodesic(geodesic: Boolean): PolylineOptions {
        this.geodesic = geodesic
        return this
    }

    fun jointType(jointType: Int): PolylineOptions {
        this.jointType = jointType
        return this
    }

    fun pattern(pattern: List<PatternItem>): PolylineOptions {
        this.pattern = pattern
        return this
    }

    fun startCap(cap: Cap): PolylineOptions {
        this.startCap = cap
        return this
    }

    fun visible(visible: Boolean): PolylineOptions {
        this.visible = visible
        return this
    }

    fun width(width: Float): PolylineOptions {
        this.width = width
        return this
    }

    fun zIndex(zIndex: Float): PolylineOptions {
        this.zIndex = zIndex
        return this
    }

    fun getPointsForGms(): List<com.google.android.gms.maps.model.LatLng> {
        return points.map { it.toGmsLatLng() }
    }

    companion object {

        @JvmStatic
        fun com.google.android.gms.maps.model.PolylineOptions.toChoice(): PolylineOptions {
            return PolylineOptions()
                .clickable(isClickable)
                .color(color)
                .endCap(endCap.toChoiceCap())
                .geodesic(isGeodesic)
                .jointType(jointType)
                .pattern(pattern.orEmpty().map { it.toChoice() })
                .startCap(startCap.toChoiceCap())
                .visible(isVisible)
                .width(width)
                .zIndex(zIndex)
                .addAll(points.map { it.toChoiceLatLng() })
        }

        internal fun PolylineOptions.toGmsPolylineOptions(): com.google.android.gms.maps.model.PolylineOptions {
            val po = com.google.android.gms.maps.model.PolylineOptions()
                .clickable(clickable)
                .color(color)
                .endCap(endCap.toGmsCap())
                .geodesic(geodesic)
                .jointType(jointType)
                .startCap(startCap.toGmsCap())
                .visible(visible)
                .width(width)
                .zIndex(zIndex)
                .addAll(points.map { it.toGmsLatLng() })

            // special case since gms does not draw anything if the list is empty
            if (pattern.isNotEmpty()) {
                po.pattern(pattern.map { it.toGmsPatternItem() })
            }
            return po
        }

        internal fun PolylineOptions.toHmsPolylineOptions(): com.huawei.hms.maps.model.PolylineOptions {
            return com.huawei.hms.maps.model.PolylineOptions()
                .clickable(clickable)
                .color(color)
                .endCap(endCap.toHmsCap())
                .geodesic(geodesic)
                .jointType(jointType)
                .pattern(pattern.map { it.toHmsPatternItem() })
                .startCap(startCap.toHmsCap())
                .visible(visible)
                .width(width)
                .zIndex(zIndex)
                .addAll(points.map { it.toHmsLatLng() })
        }
    }
}