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
class PolylineOptions : ShapeOptions() {

    val points: MutableList<LatLng> = mutableListOf()
    var isGeodesic: Boolean = false
        private set

    private var startCap: Cap = Cap.ButtCap()
    private var endCap: Cap = Cap.ButtCap()
    private var pattern: List<PatternItem> = listOf()

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

    fun geodesic(geodesic: Boolean): PolylineOptions {
        this.isGeodesic = geodesic
        return this
    }

    fun startCap(cap: Cap): PolylineOptions {
        startCap = cap
        return this
    }

    fun endCap(cap: Cap): PolylineOptions {
        endCap = cap
        return this
    }

    fun strokePattern(pattern: List<PatternItem>): PolylineOptions {
        this.pattern = pattern
        return this
    }

    fun getPointsForGms(): List<com.google.android.gms.maps.model.LatLng> {
        return points.map { it.toGmsLatLng() }
    }

    override fun clickable(clickable: Boolean): PolylineOptions {
        super.clickable(clickable)
        return this
    }

    override fun strokeColor(color: Int): PolylineOptions {
        super.strokeColor(color)
        return this
    }

    override fun strokeWidth(widthInPx: Float): PolylineOptions {
        super.strokeWidth(widthInPx)
        return this
    }

    override fun visible(visible: Boolean): PolylineOptions {
        super.visible(visible)
        return this
    }

    override fun zIndex(zIndex: Float): PolylineOptions {
        super.zIndex(zIndex)
        return this
    }

    companion object {

        @JvmStatic
        fun com.google.android.gms.maps.model.PolylineOptions.toChoice(): PolylineOptions {

            val linePoints: List<LatLng> = points.map { it.toChoiceLatLng() }
            val mappedPattern = pattern.orEmpty().map { it.toChoice() }

            return PolylineOptions()
                    .clickable(isClickable)
                    .strokeColor(color)
                    .strokeWidth(width)
                    .visible(isVisible)
                    .zIndex(zIndex)
                    .addAll(linePoints)
                    .geodesic(isGeodesic)
                    .startCap(startCap.toChoiceCap())
                    .endCap(endCap.toChoiceCap())
                    .strokePattern(mappedPattern)

        }

        internal fun PolylineOptions.toGmsPolylineOptions(): com.google.android.gms.maps.model.PolylineOptions {
            val po = com.google.android.gms.maps.model.PolylineOptions()
                    .clickable(clickable)
                    .color(strokeColor)
                    .width(strokeWidthInPx)
                    .visible(visible)
                    .zIndex(zIndex)
                    .addAll(points.map { it.toGmsLatLng() })
                    .geodesic(isGeodesic)
                    .startCap(startCap.toGmsCap())
                    .endCap(endCap.toGmsCap())

            // special case since gms does not draw anything if the list is empty
            if (pattern.isNotEmpty()) {
                po.pattern(pattern.map { it.toGmsPatternItem() })
            }
            return po
        }

        internal fun PolylineOptions.toHmsPolylineOptions(): com.huawei.hms.maps.model.PolylineOptions {
            return com.huawei.hms.maps.model.PolylineOptions()
                    .clickable(clickable)
                    .color(strokeColor)
                    .width(strokeWidthInPx)
                    .visible(visible)
                    .zIndex(zIndex)
                    .addAll(points.map { it.toHmsLatLng() })
                    .geodesic(isGeodesic)
                    .startCap(startCap.toHmsCap())
                    .endCap(endCap.toHmsCap())
                    .pattern(pattern.map { it.toHmsPatternItem() })
        }
    }
}