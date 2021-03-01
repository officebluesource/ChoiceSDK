package at.bluesource.choicesdk.maps.common.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.PatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toChoice
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toGmsPatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toHmsPatternItem

/**
 * Defines options for a [Polygon].
 * Use this Builder to add a polygon to the map.
 */
class PolygonOptions : ShapeOptions() {
    private val linePoints: MutableList<LatLng> = mutableListOf()
    internal val lineHoles: MutableList<LatLng> = mutableListOf()
    private var fillColor: Int = 0x00000000 // transparent
    var isGeodesic: Boolean = false
        private set
    private var strokeJointType: Int = Polygon.DEFAULT
    private var pattern: List<PatternItem> = listOf()

    fun add(vararg points: LatLng): PolygonOptions {
        linePoints.addAll(points)
        return this
    }

    fun add(point: LatLng): PolygonOptions {
        linePoints.add(point)
        return this
    }

    fun addAll(points: Iterable<LatLng>): PolygonOptions {
        linePoints.addAll(points)
        return this
    }

    fun addHole(points: Iterable<LatLng>): PolygonOptions {
        lineHoles.addAll(points)
        return this
    }

    fun fillColor(color: Int): PolygonOptions {
        fillColor = color
        return this
    }

    fun geodesic(geodesic: Boolean): PolygonOptions {
        this.isGeodesic = geodesic
        return this
    }

    fun strokeType(type: Int): PolygonOptions {
        when (type) {
            in listOf(Polygon.BEVEL, Polygon.DEFAULT, Polygon.ROUND) -> strokeJointType = type
        }
        return this
    }

    fun strokePattern(pattern: List<PatternItem>): PolygonOptions {
        this.pattern = pattern
        return this
    }

    fun strokeJointType(strokeJointType: Int): PolygonOptions {
        this.strokeJointType = strokeJointType
        return this
    }

    fun getPointsForGms(): List<com.google.android.gms.maps.model.LatLng> {
        return linePoints.map { it.toGmsLatLng() }
    }

    override fun clickable(clickable: Boolean): PolygonOptions {
        super.clickable(clickable)
        return this
    }

    override fun strokeColor(color: Int): PolygonOptions {
        super.strokeColor(color)
        return this
    }

    override fun strokeWidth(widthInPx: Float): PolygonOptions {
        super.strokeWidth(widthInPx)
        return this
    }

    override fun visible(visible: Boolean): PolygonOptions {
        super.visible(visible)
        return this
    }

    override fun zIndex(zIndex: Float): PolygonOptions {
        super.zIndex(zIndex)
        return this
    }

    companion object {

        @JvmStatic
        fun com.google.android.gms.maps.model.PolygonOptions.toChoice(): PolygonOptions {

            val linePoints: List<LatLng> = this.points.map { it.toChoiceLatLng() }
            val pattern: List<PatternItem> = this.strokePattern.orEmpty().map { it.toChoice() }
            val mappedHoles: List<List<LatLng>> = this.holes.map { hole -> hole.map { it.toChoiceLatLng() } }

            val options = PolygonOptions()
                    .clickable(isClickable)
                    .fillColor(fillColor)
                    .strokeColor(strokeColor)
                    .strokeWidth(strokeWidth)
                    .visible(isVisible)
                    .zIndex(zIndex)
                    .addAll(linePoints)
                    .geodesic(isGeodesic)
                    .strokeJointType(strokeJointType)
                    .strokePattern(pattern)

            mappedHoles.forEach { hole -> options.addHole(hole) }

            return options
        }

        internal fun PolygonOptions.toGmsPolygonOptions(): com.google.android.gms.maps.model.PolygonOptions {
            val po = com.google.android.gms.maps.model.PolygonOptions()
                    .clickable(clickable)
                    .fillColor(fillColor)
                    .strokeColor(strokeColor)
                    .strokeWidth(strokeWidthInPx)
                    .visible(visible)
                    .zIndex(zIndex)
                    .addAll(linePoints.map { it.toGmsLatLng() })
                    .geodesic(isGeodesic)
                    .strokeJointType(strokeJointType)


            // special case since gms adds an empty hole if list is empty
            // which leads to an ApiException
            if (lineHoles.size > 0) {
                po.addHole(lineHoles.map { it.toGmsLatLng() })
            }

            if (pattern.isNotEmpty()) {
                po.strokePattern(pattern.map { it.toGmsPatternItem() })
            }
            return po
        }

        internal fun PolygonOptions.toHmsPolygonOptions(): com.huawei.hms.maps.model.PolygonOptions {
            return com.huawei.hms.maps.model.PolygonOptions()
                    .clickable(clickable)
                    .fillColor(fillColor)
                    .strokeColor(strokeColor)
                    .strokeWidth(strokeWidthInPx)
                    .visible(visible)
                    .zIndex(zIndex)
                    .addAll(linePoints.map { it.toHmsLatLng() })
                    .addHole(lineHoles.map { it.toHmsLatLng() })
                    .geodesic(isGeodesic)
                    .strokeJointType(strokeJointType)
                    .strokePattern(pattern.map { it.toHmsPatternItem() })
        }
    }
}