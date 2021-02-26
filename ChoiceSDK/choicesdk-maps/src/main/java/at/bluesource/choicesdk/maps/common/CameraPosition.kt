package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng


/**
 * Common interface for hms and gms camera position
 *
 * An immutable class that aggregates all camera position parameters such as location, zoom level, tilt angle, and bearing.
 * Use [CameraPosition.Builder] to construct a CameraPosition instance,
 * which you can then use in conjunction with [CameraUpdateFactory].
 *
 * @see com.google.android.gms.maps.model.CameraPosition
 * @see com.huawei.hms.maps.model.CameraPosition
 */
interface CameraPosition {

    val bearing: Float
    val target: LatLng
    val tilt: Float
    val zoom: Float

    /**
     * Builds a [CameraPosition].
     */
    @Suppress("unused")
    class Builder {
        private var bearing: Float = 0f
        private var target: LatLng =
                LatLng(0.0, 0.0)
        private var tilt: Float = 0f
        private var zoom: Float = 0f

        fun setBearing(bearing: Float): Builder {
            this.bearing = bearing
            return this
        }

        fun setTarget(target: LatLng): Builder {
            this.target = target
            return this
        }

        fun setTilt(tilt: Float): Builder {
            this.tilt = tilt
            return this
        }

        fun setZoom(zoom: Float): Builder {
            this.zoom = zoom
            return this
        }

        fun build(): CameraPosition {
            return object : CameraPosition {
                override val bearing: Float
                    get() = this@Builder.bearing
                override val target: LatLng
                    get() = this@Builder.target
                override val tilt: Float
                    get() = this@Builder.tilt
                override val zoom: Float
                    get() = this@Builder.zoom
            }
        }
    }

    companion object {

        internal fun CameraPosition.toGmsCameraPosition(): com.google.android.gms.maps.model.CameraPosition {
            return com.google.android.gms.maps.model.CameraPosition.Builder()
                    .bearing(bearing)
                    .target(target.toGmsLatLng())
                    .tilt(tilt)
                    .zoom(zoom)
                    .build()
        }

        internal fun CameraPosition.toHmsCameraPosition(): com.huawei.hms.maps.model.CameraPosition {
            return com.huawei.hms.maps.model.CameraPosition.Builder()
                    .bearing(bearing)
                    .target(target.toHmsLatLng())
                    .tilt(tilt)
                    .zoom(zoom)
                    .build()
        }

        internal fun com.google.android.gms.maps.model.CameraPosition.toChoiceCameraPosition(): CameraPosition {
            return object : CameraPosition {
                override val bearing: Float
                    get() = this@toChoiceCameraPosition.bearing
                override val target: LatLng
                    get() = this@toChoiceCameraPosition.target.toChoiceLatLng()
                override val tilt: Float
                    get() = this@toChoiceCameraPosition.tilt
                override val zoom: Float
                    get() = this@toChoiceCameraPosition.zoom
            }
        }

        internal fun com.huawei.hms.maps.model.CameraPosition.toChoiceCameraPosition(): CameraPosition {
            return object : CameraPosition {
                override val bearing: Float
                    get() = this@toChoiceCameraPosition.bearing
                override val target: LatLng
                    get() = this@toChoiceCameraPosition.target.toChoiceLatLng()
                override val tilt: Float
                    get() = this@toChoiceCameraPosition.tilt
                override val zoom: Float
                    get() = this@toChoiceCameraPosition.zoom
            }
        }
    }
}