package at.bluesource.choicesdk.maps.common.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.PatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toGmsPatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toHmsPatternItem

/**
 * Defines options for a [Circle].
 * Use this Builder to add a circle to the map.
 */
class CircleOptions {
    private var center: LatLng = LatLng(0.0, 0.0)
    private var clickable = false
    private var fillColor: Int = 0x00000000 // transparent
    private var radius = 1.0
    private var strokeColor: Int = 0xff000000.toInt() // black, also toInt workaround for compiler issue: https://youtrack.jetbrains.com/issue/KT-4749
    private var strokePattern: List<PatternItem> = listOf()
    private var strokeWidth: Float = 10f
    private var visible = true
    private var zIndex: Float = 0f

    fun center(center: LatLng): CircleOptions {
        this.center = center
        return this
    }

    fun clickable(clickable: Boolean): CircleOptions {
        this.clickable = clickable
        return this
    }

    fun fillColor(color: Int): CircleOptions {
        this.fillColor = color
        return this
    }

    fun radius(radius: Double): CircleOptions {
        this.radius = radius
        return this
    }

    fun strokeColor(color: Int): CircleOptions {
        this.strokeColor = color
        return this
    }

    fun strokePattern(pattern: List<PatternItem>): CircleOptions {
        this.strokePattern = pattern
        return this
    }

    fun strokeWidth(width: Float): CircleOptions {
        this.strokeWidth = width
        return this
    }

    fun visible(visible: Boolean): CircleOptions {
        this.visible = visible
        return this
    }

    fun zIndex(zIndex: Float): CircleOptions {
        this.zIndex = zIndex
        return this
    }

    companion object {
        internal fun CircleOptions.toGmsCircleOptions(): com.google.android.gms.maps.model.CircleOptions {
            val co = com.google.android.gms.maps.model.CircleOptions()
                .center(center.toGmsLatLng())
                .clickable(clickable)
                .fillColor(fillColor)
                .radius(radius)
                .strokeColor(strokeColor)
                .strokeWidth(strokeWidth)
                .visible(visible)
                .zIndex(zIndex)

            // special case since gms does not draw anything if the list is empty
            if (strokePattern.isNotEmpty()) {
                co.strokePattern(strokePattern.map { it.toGmsPatternItem() })
            }
            return co
        }

        internal fun CircleOptions.toHmsCircleOptions(): com.huawei.hms.maps.model.CircleOptions {
            return com.huawei.hms.maps.model.CircleOptions()
                .center(center.toHmsLatLng())
                .clickable(clickable)
                .fillColor(fillColor)
                .radius(radius)
                .strokeColor(strokeColor)
                .strokeWidth(strokeWidth)
                .visible(visible)
                .zIndex(zIndex)
                .strokePattern(strokePattern.map { it.toHmsPatternItem() })
        }
    }
}