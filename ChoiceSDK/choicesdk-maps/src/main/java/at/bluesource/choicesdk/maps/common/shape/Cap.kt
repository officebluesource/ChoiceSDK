package at.bluesource.choicesdk.maps.common.shape

import at.bluesource.choicesdk.maps.common.BitmapDescriptor
import at.bluesource.choicesdk.maps.common.shape.Cap.ButtCap.Companion.toGmsButtCap
import at.bluesource.choicesdk.maps.common.shape.Cap.ButtCap.Companion.toHmsButtCap
import at.bluesource.choicesdk.maps.common.shape.Cap.CustomCap.Companion.toGmsCustomCap
import at.bluesource.choicesdk.maps.common.shape.Cap.CustomCap.Companion.toHmsCustomCap
import at.bluesource.choicesdk.maps.common.shape.Cap.RoundCap.Companion.toGmsRoundCap
import at.bluesource.choicesdk.maps.common.shape.Cap.RoundCap.Companion.toHmsRoundCap
import at.bluesource.choicesdk.maps.common.shape.Cap.SquareCap.Companion.toGmsSquareCap
import at.bluesource.choicesdk.maps.common.shape.Cap.SquareCap.Companion.toHmsSquareCap

/**
 * Wrapper class for hms and gms cap
 * Has inner classes which hold their specific version
 *
 * @see com.google.android.gms.maps.model.Cap
 * @see com.huawei.hms.maps.model.Cap
 */
@Suppress("unused")
sealed class Cap {

    /**
     * Cap that is squared off exactly at the start or end vertex of a Polyline with solid
     * stroke pattern, equivalent to having no additional cap beyond the start or end vertex.
     * This is the default cap type at start and end vertices of a [Polyline] with solid stroke pattern.
     */
    class ButtCap : Cap() {
        companion object {
            fun ButtCap.toGmsButtCap(): com.google.android.gms.maps.model.ButtCap {
                return com.google.android.gms.maps.model.ButtCap()
            }

            fun ButtCap.toHmsButtCap(): com.huawei.hms.maps.model.ButtCap {
                return com.huawei.hms.maps.model.ButtCap()
            }
        }
    }

    /**
     * Bitmap overlay centered at the start or end vertex of a Polyline, orientated according to
     * the direction of the line's first or last edge and scaled with respect to
     * the line's stroke width. CustomCap can be applied to [Polyline] with any stroke pattern.
     */
    class CustomCap(var bitmapDescriptor: BitmapDescriptor) : Cap() {
        private var refWidth = -1f

        constructor(bitmapDescriptor: BitmapDescriptor, refWidth: Float) : this(bitmapDescriptor) {
            this.refWidth = refWidth
        }

        companion object {
            fun CustomCap.toGmsCustomCap(): com.google.android.gms.maps.model.CustomCap {
                val gmsDescriptor = when (bitmapDescriptor) {
                    is BitmapDescriptor.GmsBitmapDescriptor -> (bitmapDescriptor as BitmapDescriptor.GmsBitmapDescriptor).value
                    else -> com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker()
                }

                return when (refWidth) {
                    -1f -> com.google.android.gms.maps.model.CustomCap(gmsDescriptor)
                    else -> com.google.android.gms.maps.model.CustomCap(gmsDescriptor, refWidth)
                }
            }

            fun CustomCap.toHmsCustomCap(): com.huawei.hms.maps.model.CustomCap {
                val hmsDescriptor = when (bitmapDescriptor) {
                    is BitmapDescriptor.HmsBitmapDescriptor -> (bitmapDescriptor as BitmapDescriptor.HmsBitmapDescriptor).value
                    else -> com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker()
                }

                return when (refWidth) {
                    -1f -> com.huawei.hms.maps.model.CustomCap(hmsDescriptor)
                    else -> com.huawei.hms.maps.model.CustomCap(
                        hmsDescriptor, refWidth
                    )
                }
            }
        }
    }

    /**
     * Cap that is a semicircle with radius equal to half the stroke width,
     * centered at the start or end vertex of a [Polyline] with solid stroke pattern.
     */
    class RoundCap : Cap() {
        companion object {
            fun RoundCap.toGmsRoundCap(): com.google.android.gms.maps.model.RoundCap {
                return com.google.android.gms.maps.model.RoundCap()
            }

            fun RoundCap.toHmsRoundCap(): com.huawei.hms.maps.model.RoundCap {
                return com.huawei.hms.maps.model.RoundCap()
            }
        }
    }

    /**
     * Cap that is squared off after extending half the stroke width beyond the start
     * or end vertex of a Polyline with solid stroke pattern.
     */
    class SquareCap : Cap() {
        companion object {
            fun SquareCap.toGmsSquareCap(): com.google.android.gms.maps.model.SquareCap {
                return com.google.android.gms.maps.model.SquareCap()
            }

            fun SquareCap.toHmsSquareCap(): com.huawei.hms.maps.model.SquareCap {
                return com.huawei.hms.maps.model.SquareCap()
            }
        }
    }

    companion object {
        internal fun Cap.toGmsCap(): com.google.android.gms.maps.model.Cap {
            return when (this) {
                is ButtCap -> this.toGmsButtCap()
                is CustomCap -> this.toGmsCustomCap()
                is RoundCap -> this.toGmsRoundCap()
                is SquareCap -> this.toGmsSquareCap()
            }
        }

        internal fun Cap.toHmsCap(): com.huawei.hms.maps.model.Cap {
            return when (this) {
                is ButtCap -> this.toHmsButtCap()
                is CustomCap -> this.toHmsCustomCap()
                is RoundCap -> this.toHmsRoundCap()
                is SquareCap -> this.toHmsSquareCap()
            }
        }

        internal fun com.google.android.gms.maps.model.Cap.toChoiceCap(): Cap {
            return when (this) {
                is com.google.android.gms.maps.model.ButtCap -> ButtCap()
                is com.google.android.gms.maps.model.CustomCap -> CustomCap(
                    BitmapDescriptor.GmsBitmapDescriptor(this.bitmapDescriptor),
                    this.refWidth
                )
                is com.google.android.gms.maps.model.RoundCap -> RoundCap()
                is com.google.android.gms.maps.model.SquareCap -> SquareCap()
                else -> ButtCap()
            }
        }

        internal fun com.huawei.hms.maps.model.Cap.toChoiceCap(): Cap {
            return when (this) {
                is com.huawei.hms.maps.model.ButtCap -> ButtCap()
                is com.huawei.hms.maps.model.CustomCap -> CustomCap(
                    BitmapDescriptor.HmsBitmapDescriptor(this.bitmapDescriptor),
                    this.refWidth
                )
                is com.huawei.hms.maps.model.RoundCap -> RoundCap()
                is com.huawei.hms.maps.model.SquareCap -> SquareCap()
                else -> ButtCap()
            }
        }
    }
}