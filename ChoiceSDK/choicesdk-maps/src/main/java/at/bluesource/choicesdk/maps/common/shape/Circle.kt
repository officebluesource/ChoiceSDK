@file:Suppress("unused")

package at.bluesource.choicesdk.maps.common.shape

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.PatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toGmsPatternItem
import at.bluesource.choicesdk.maps.common.PatternItem.Companion.toHmsPatternItem
import at.bluesource.choicesdk.maps.gms.shape.GmsCircle
import at.bluesource.choicesdk.maps.hms.shape.HmsCircle

/**
 * A circle on the earth's surface (spherical cap).
 */
interface Circle : Shape {
    var center: LatLng
    var radius: Double
    var fillColor: Int

    /**
     * Defines options for a [Circle].
     * Use this Builder to add a circle to the map.
     */
    class CircleOptions : ShapeOptions() {
        private var center: LatLng = LatLng(0.0, 0.0)
        private var fillColor: Int = 0x00000000 // transparent
        private var radius = 1.0
        private var pattern: List<PatternItem> = listOf()

        fun center(center: LatLng): CircleOptions {
            this.center = center
            return this
        }

        fun fillColor(color: Int): CircleOptions {
            fillColor = color
            return this
        }

        fun radius(radius: Double): CircleOptions {
            this.radius = radius
            return this
        }

        fun strokePattern(pattern: List<PatternItem>): CircleOptions {
            this.pattern = pattern
            return this
        }

        override fun clickable(clickable: Boolean): CircleOptions {
            super.clickable(clickable)
            return this
        }

        override fun strokeColor(color: Int): CircleOptions {
            super.strokeColor(color)
            return this
        }

        override fun strokeWidth(widthInPx: Float): CircleOptions {
            super.strokeWidth(widthInPx)
            return this
        }

        override fun visible(visible: Boolean): CircleOptions {
            super.visible(visible)
            return this
        }

        override fun zIndex(zIndex: Float): CircleOptions {
            super.zIndex(zIndex)
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
                    .strokeWidth(strokeWidthInPx)
                    .visible(visible)
                    .zIndex(zIndex)
                    .strokePattern(pattern.map { it.toGmsPatternItem() })

                // special case since gms does not draw anything if the list is empty
                if (pattern.isNotEmpty()) {
                    co.strokePattern(pattern.map { it.toGmsPatternItem() })
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
                    .strokeWidth(strokeWidthInPx)
                    .visible(visible)
                    .zIndex(zIndex)
                    .strokePattern(pattern.map { it.toHmsPatternItem() })
            }
        }
    }

    companion object {
        internal fun com.google.android.gms.maps.model.Circle.toChoiceCircle(): Circle {
            return GmsCircle(this@toChoiceCircle)
        }

        internal fun com.huawei.hms.maps.model.Circle.toChoiceCircle(): Circle {
            return HmsCircle(this@toChoiceCircle)
        }
    }
}