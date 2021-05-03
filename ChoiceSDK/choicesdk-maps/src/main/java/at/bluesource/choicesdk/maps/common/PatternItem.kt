package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.maps.common.PatternItem.Dash.Companion.toGmsDash
import at.bluesource.choicesdk.maps.common.PatternItem.Dash.Companion.toHmsDash
import at.bluesource.choicesdk.maps.common.PatternItem.Dot.Companion.toGmsDot
import at.bluesource.choicesdk.maps.common.PatternItem.Dot.Companion.toHmsDot
import at.bluesource.choicesdk.maps.common.PatternItem.Gap.Companion.toGmsGap
import at.bluesource.choicesdk.maps.common.PatternItem.Gap.Companion.toHmsGap

/**
 * Wrapper class for hms and gms PatternItem
 * Has inner classes which hold their specific version
 *
 * @see com.google.android.gms.maps.model.PatternItem
 * @see com.huawei.hms.maps.model.PatternItem
 */
sealed class PatternItem {
    /**
     * A class representing a dash used in the stroke pattern for a Polyline or the outline of a Polygon or Circle.
     *
     * @property length Length in pixels (non-negative)
     */
    class Dash(private val length: Float) : PatternItem() {
        companion object {
            internal fun Dash.toGmsDash(): com.google.android.gms.maps.model.Dash {
                return com.google.android.gms.maps.model.Dash(length)
            }

            internal fun Dash.toHmsDash(): com.huawei.hms.maps.model.Dash {
                return com.huawei.hms.maps.model.Dash(length)
            }
        }
    }

    /**
     * A class representing a dot used in the stroke pattern for a Polyline or the outline of a Polygon or Circle.
     */
    class Dot : PatternItem() {
        companion object {
            internal fun toGmsDot(): com.google.android.gms.maps.model.Dot {
                return com.google.android.gms.maps.model.Dot()
            }

            internal fun toHmsDot(): com.huawei.hms.maps.model.Dot {
                return com.huawei.hms.maps.model.Dot()
            }
        }
    }

    /**
     * A class representing a gap used in the stroke pattern for a Polyline or the outline of a Polygon or Circle.
     *
     * @property length Length in pixels (non-negative)
     */
    class Gap(private val length: Float) : PatternItem() {
        companion object {
            internal fun Gap.toGmsGap(): com.google.android.gms.maps.model.Gap {
                return com.google.android.gms.maps.model.Gap(length)
            }

            internal fun Gap.toHmsGap(): com.huawei.hms.maps.model.Gap {
                return com.huawei.hms.maps.model.Gap(length)
            }
        }
    }

    companion object {

        internal fun com.google.android.gms.maps.model.PatternItem.toChoice(): PatternItem {
            return when (this) {
                is com.google.android.gms.maps.model.Dash -> Dash(length = this.length)
                is com.google.android.gms.maps.model.Dot -> Dot()
                is com.google.android.gms.maps.model.Gap -> Gap(length = this.length)
                else -> throw IllegalArgumentException("No implementation provided for PatternItem of type: ${this.javaClass}")
            }
        }

        internal fun PatternItem.toGmsPatternItem(): com.google.android.gms.maps.model.PatternItem {
            return when (this) {
                is Dash -> this.toGmsDash()
                is Dot -> toGmsDot()
                is Gap -> this.toGmsGap()
            }
        }

        internal fun PatternItem.toHmsPatternItem(): com.huawei.hms.maps.model.PatternItem {
            return when (this) {
                is Dash -> this.toHmsDash()
                is Dot -> toHmsDot()
                is Gap -> this.toHmsGap()
            }
        }
    }
}