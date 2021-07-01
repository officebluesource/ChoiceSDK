package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.gms.GmsLatLngBounds
import at.bluesource.choicesdk.maps.gms.GmsLatLngBoundsBuilder
import at.bluesource.choicesdk.maps.gms.GmsLatLngBoundsFactory
import at.bluesource.choicesdk.maps.hms.HmsLatLngBounds
import at.bluesource.choicesdk.maps.hms.HmsLatLngBoundsBuilder
import at.bluesource.choicesdk.maps.hms.HmsLatLngBoundsFactory

/**
 * A class representing a latitude/longitude aligned rectangle.
 *
 * --------------ne
 *
 * |             |
 *
 * |      x      |
 *
 * |             |
 *
 * sw--------------
 *
 * @property southwest [LatLng] for the southwest corner
 * @property northeast [LatLng] for the northeast corner
 * @property center the midpoint between the two corners
 */
interface LatLngBounds {

    val southwest: LatLng
    val northeast: LatLng

    fun contains(point: LatLng): Boolean
    fun including(point: LatLng): LatLngBounds
    fun getCenter(): LatLng

    interface Builder {
        fun include(point: LatLng): Builder
        fun build(): LatLngBounds
    }

    interface Factory {
        fun create(southwest: LatLng, northeast: LatLng): LatLngBounds
    }

    companion object {

        fun getBuilder(): Builder {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsLatLngBoundsBuilder()
                MobileService.HMS -> HmsLatLngBoundsBuilder()
            }
        }

        @JvmStatic
        fun getFactory(): Factory {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsLatLngBoundsFactory()
                MobileService.HMS -> HmsLatLngBoundsFactory()
            }
        }

        @JvmStatic
        fun com.google.android.gms.maps.model.LatLngBounds.toChoiceLatLngBounds(): LatLngBounds {
            return GmsLatLngBounds(this)
        }

        fun com.huawei.hms.maps.model.LatLngBounds.toChoiceLatLngBounds(): LatLngBounds {
            return HmsLatLngBounds(this)
        }

        @JvmStatic
        fun LatLngBounds.toGmsLatLngBounds(): com.google.android.gms.maps.model.LatLngBounds {
            return com.google.android.gms.maps.model.LatLngBounds(
                southwest.toGmsLatLng(),
                northeast.toGmsLatLng()
            )
        }

        internal fun LatLngBounds.toHmsLatLngBounds(): com.huawei.hms.maps.model.LatLngBounds {
            return com.huawei.hms.maps.model.LatLngBounds(
                southwest.toHmsLatLng(),
                northeast.toHmsLatLng()
            )
        }
    }
}