package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toChoiceLatLngBounds

/**
 * Common interface for hms and gms VisibleRegion
 *
 * Contains the four points defining the four-sided polygon that is visible in a map's camera.
 * This polygon can be a trapezoid instead of a rectangle, because a camera can have tilt.
 * If the camera is directly over the center of the camera, the shape is rectangular, but if the camera is tilted,
 * the shape will appear to be a trapezoid whose smallest side is closest to the point of view.
 *
 * @see com.google.android.gms.maps.model.VisibleRegion
 * @see com.huawei.hms.maps.model.VisibleRegion
 */
interface VisibleRegion {
    val farLeft: LatLng
    val farRight: LatLng
    val nearLeft: LatLng
    val nearRight: LatLng
    val latLngBounds: LatLngBounds

    companion object {
        internal fun com.google.android.gms.maps.model.VisibleRegion.toChoiceVisibleRegion(): VisibleRegion {
            return object : VisibleRegion {
                override val farLeft: LatLng
                    get() = this@toChoiceVisibleRegion.farLeft.toChoiceLatLng()
                override val farRight: LatLng
                    get() = this@toChoiceVisibleRegion.farRight.toChoiceLatLng()
                override val nearLeft: LatLng
                    get() = this@toChoiceVisibleRegion.nearLeft.toChoiceLatLng()
                override val nearRight: LatLng
                    get() = this@toChoiceVisibleRegion.nearRight.toChoiceLatLng()
                override val latLngBounds: LatLngBounds
                    get() = this@toChoiceVisibleRegion.latLngBounds.toChoiceLatLngBounds()
            }
        }

        internal fun com.huawei.hms.maps.model.VisibleRegion.toChoiceVisibleRegion(): VisibleRegion {
            return object : VisibleRegion {
                override val farLeft: LatLng
                    get() = this@toChoiceVisibleRegion.farLeft.toChoiceLatLng()
                override val farRight: LatLng
                    get() = this@toChoiceVisibleRegion.farRight.toChoiceLatLng()
                override val nearLeft: LatLng
                    get() = this@toChoiceVisibleRegion.nearLeft.toChoiceLatLng()
                override val nearRight: LatLng
                    get() = this@toChoiceVisibleRegion.nearRight.toChoiceLatLng()
                override val latLngBounds: LatLngBounds
                    get() = this@toChoiceVisibleRegion.latLngBounds.toChoiceLatLngBounds()
            }
        }
    }
}