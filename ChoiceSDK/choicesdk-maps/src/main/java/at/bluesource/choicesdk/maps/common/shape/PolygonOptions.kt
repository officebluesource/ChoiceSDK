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
class PolygonOptions {
    private val holes: MutableList<LatLng> = mutableListOf()
    private val points: MutableList<LatLng> = mutableListOf()
    private var clickable = false
    private var fillColor: Int = 0x00000000 // transparent
    private var geodesic: Boolean = false
    private var strokeColor: Int = 0xff000000.toInt() // black, also toInt workaround for compiler issue: https://youtrack.jetbrains.com/issue/KT-4749
    private var strokeJointType: Int = Polygon.DEFAULT
    private var strokePattern: List<PatternItem> = listOf()
    private var strokeWidth: Float = 10f
    private var visible = true
    private var zIndex: Float = 0f

    fun add(vararg points: LatLng): PolygonOptions {
        this.points.addAll(points)
        return this
    }

    fun add(point: LatLng): PolygonOptions {
        this.points.add(point)
        return this
    }

    fun addAll(points: Iterable<LatLng>): PolygonOptions {
        this.points.addAll(points)
        return this
    }

    fun addHole(points: Iterable<LatLng>): PolygonOptions {
        this.holes.addAll(points)
        return this
    }

    fun clickable(clickable: Boolean): PolygonOptions {
        this.clickable = clickable
        return this
    }

    fun fillColor(color: Int): PolygonOptions {
        this.fillColor = color
        return this
    }

    fun geodesic(geodesic: Boolean): PolygonOptions {
        this.geodesic = geodesic
        return this
    }

    fun strokeColor(color: Int): PolygonOptions {
        this.strokeColor = color
        return this
    }

    fun strokeJointType(strokeJointType: Int): PolygonOptions {
        this.strokeJointType = strokeJointType
        return this
    }

    fun strokePattern(pattern: List<PatternItem>): PolygonOptions {
        this.strokePattern = pattern
        return this
    }

    fun strokeWidth(width: Float): PolygonOptions {
        this.strokeWidth = width
        return this
    }

    fun visible(visible: Boolean): PolygonOptions {
        this.visible = visible
        return this
    }

    fun zIndex(zIndex: Float): PolygonOptions {
        this.zIndex = zIndex
        return this
    }

    fun strokeType(type: Int): PolygonOptions {
        when (type) {
            in listOf(Polygon.BEVEL, Polygon.DEFAULT, Polygon.ROUND) -> strokeJointType = type
        }
        return this
    }

    fun getPointsForGms(): List<com.google.android.gms.maps.model.LatLng> {
        return points.map { it.toGmsLatLng() }
    }

    companion object {

        @JvmStatic
        fun com.google.android.gms.maps.model.PolygonOptions.toChoice(): PolygonOptions {

            val holes: List<List<LatLng>> = this.holes.map { hole -> hole.map { it.toChoiceLatLng() } }

            val options = PolygonOptions()
                .clickable(isClickable)
                .fillColor(fillColor)
                .geodesic(isGeodesic)
                .strokeColor(strokeColor)
                .strokeJointType(strokeJointType)
                .strokePattern(strokePattern.orEmpty().map { it.toChoice() })
                .strokeWidth(strokeWidth)
                .visible(isVisible)
                .zIndex(zIndex)

            options.addAll(points.map { it.toChoiceLatLng() })
            holes.forEach { hole -> options.addHole(hole) }

            return options
        }

        internal fun PolygonOptions.toGmsPolygonOptions(): com.google.android.gms.maps.model.PolygonOptions {
            val po = com.google.android.gms.maps.model.PolygonOptions()
                .clickable(clickable)
                .fillColor(fillColor)
                .geodesic(geodesic)
                .strokeColor(strokeColor)
                .strokeJointType(strokeJointType)
                .strokeWidth(strokeWidth)
                .visible(visible)
                .zIndex(zIndex)

            po.addAll(points.map { it.toGmsLatLng() })

            // special case since gms adds an empty hole if list is empty
            // which leads to an ApiException
            if (holes.size > 0) {
                po.addHole(holes.map { it.toGmsLatLng() })
            }
            if (strokePattern.isNotEmpty()) {
                po.strokePattern(strokePattern.map { it.toGmsPatternItem() })
            }

            return po
        }

        internal fun PolygonOptions.toHmsPolygonOptions(): com.huawei.hms.maps.model.PolygonOptions {
            return com.huawei.hms.maps.model.PolygonOptions()
                .clickable(clickable)
                .fillColor(fillColor)
                .geodesic(geodesic)
                .strokeColor(strokeColor)
                .strokeJointType(strokeJointType)
                .strokePattern(strokePattern.map { it.toHmsPatternItem() })
                .strokeWidth(strokeWidth)
                .visible(visible)
                .zIndex(zIndex)
                .addAll(points.map { it.toHmsLatLng() })
                .addHole(holes.map { it.toHmsLatLng() })
        }
    }
}