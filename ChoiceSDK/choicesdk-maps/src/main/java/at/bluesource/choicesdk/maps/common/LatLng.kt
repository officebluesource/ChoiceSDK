package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.gms.GmsLatLngFactory
import at.bluesource.choicesdk.maps.hms.HmsLatLngFactory
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**


 */
/**
 * Wrapper for hms and gms LatLng
 *
 * A class representing a pair of latitude and longitude coordinates, stored as degrees.
 *
 * @property latitude Latitude, in degrees.
 * @property longitude Longitude, in degrees.
 * @see com.google.android.gms.maps.model.LatLng
 * @see com.huawei.hms.maps.model.LatLng
 */
data class LatLng(var latitude: Double, var longitude: Double) {

    interface Factory {
        fun create(latitude: Double, longitude: Double): LatLng
    }

    companion object {

        // reference: https://www.movable-type.co.uk/scripts/latlong.html
        fun midPoint(latLng1: LatLng, latLng2: LatLng): LatLng {
            val dLon = Math.toRadians(latLng2.longitude - latLng1.longitude)
            //convert to radians
            val lat1 = Math.toRadians(latLng1.latitude)
            val lat2 = Math.toRadians(latLng2.latitude)
            val lon1 = Math.toRadians(latLng1.longitude)
            val bx = cos(lat2) * cos(dLon)
            val by = cos(lat2) * sin(dLon)
            val lat3 = atan2(
                sin(lat1) + sin(lat2),
                sqrt((cos(lat1) + bx) * (cos(lat1) + bx) + by * by)
            )
            val lon3 = lon1 + atan2(by, cos(lat1) + bx)
            return LatLng(Math.toDegrees(lat3), Math.toDegrees(lon3))
        }

        @JvmStatic
        fun getFactory(): Factory {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsLatLngFactory()
                MobileService.HMS -> HmsLatLngFactory()
            }
        }

        @JvmStatic
        fun com.google.android.gms.maps.model.LatLng.toChoiceLatLng(): LatLng {
            return LatLng(
                latitude,
                longitude
            )
        }

        @JvmStatic
        fun List<com.google.android.gms.maps.model.LatLng>?.toChoiceLatLng(): List<LatLng> {
            return this.orEmpty().map { it.toChoiceLatLng() }
        }

        @JvmStatic
        fun com.huawei.hms.maps.model.LatLng.toChoiceLatLng(): LatLng {
            return LatLng(
                latitude,
                longitude
            )
        }

        @JvmStatic
        fun LatLng.toGmsLatLng(): com.google.android.gms.maps.model.LatLng {
            return com.google.android.gms.maps.model.LatLng(latitude, longitude)
        }

        fun LatLng.toHmsLatLng(): com.huawei.hms.maps.model.LatLng {
            return com.huawei.hms.maps.model.LatLng(latitude, longitude)
        }
    }
}