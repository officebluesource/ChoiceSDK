package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng

/**
 * Common interface for gms and hms PointOfInterest
 *
 * Contains information about a PointOfInterest that was clicked on.
 *
 * @see com.google.android.gms.maps.model.PointOfInterest
 * @see com.huawei.hms.maps.model.PointOfInterest
 */
interface PointOfInterest {
    val latLng: LatLng
    val name: String
    val placeId: String

    companion object {
        internal fun com.google.android.gms.maps.model.PointOfInterest.toChoicePoi(): PointOfInterest {
            return object : PointOfInterest {
                override val latLng: LatLng
                    get() = this@toChoicePoi.latLng.toChoiceLatLng()
                override val name: String
                    get() = this@toChoicePoi.name
                override val placeId: String
                    get() = this@toChoicePoi.placeId
            }
        }

        internal fun com.huawei.hms.maps.model.PointOfInterest.toChoicePoi(): PointOfInterest {
            return object : PointOfInterest {
                override val latLng: LatLng
                    get() = this@toChoicePoi.latLng.toChoiceLatLng()
                override val name: String
                    get() = this@toChoicePoi.name
                override val placeId: String
                    get() = this@toChoicePoi.placeId
            }
        }
    }
}