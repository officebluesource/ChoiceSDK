package at.bluesource.choicesdk.maps.common

import android.graphics.Point
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.VisibleRegion.Companion.toChoiceVisibleRegion

/**
 * Common interface for hms and gms Projection
 *
 * A projection is used to translate between on screen location and geographic coordinates on the surface of the Earth (LatLng).
 * Screen location is in screen pixels (not display pixels) with respect to the top left corner of the map (and not necessarily of the whole screen).
 *
 * @see com.google.android.gms.maps.Projection
 * @see com.huawei.hms.maps.Projection
 */
interface Projection {
    fun fromScreenLocation(point: Point): LatLng
    fun toScreenLocation(latLng: LatLng): Point
    fun getVisibleRegion(): VisibleRegion

    companion object {
        internal fun com.google.android.gms.maps.Projection.toChoiceProjection(): Projection {
            return object : Projection {
                override fun fromScreenLocation(point: Point): LatLng {
                    return this@toChoiceProjection.fromScreenLocation(point).toChoiceLatLng()
                }

                override fun toScreenLocation(latLng: LatLng): Point {
                    return this@toChoiceProjection.toScreenLocation(latLng.toGmsLatLng())
                }

                override fun getVisibleRegion(): VisibleRegion {
                    return this@toChoiceProjection.visibleRegion.toChoiceVisibleRegion()
                }
            }
        }

        internal fun com.huawei.hms.maps.Projection.toChoiceProjection(): Projection {
            return object : Projection {
                override fun fromScreenLocation(point: Point): LatLng {
                    return this@toChoiceProjection.fromScreenLocation(point).toChoiceLatLng()
                }

                override fun toScreenLocation(latLng: LatLng): Point {
                    return this@toChoiceProjection.toScreenLocation(latLng.toHmsLatLng())
                }

                override fun getVisibleRegion(): VisibleRegion {
                    return this@toChoiceProjection.visibleRegion.toChoiceVisibleRegion()
                }
            }
        }
    }
}