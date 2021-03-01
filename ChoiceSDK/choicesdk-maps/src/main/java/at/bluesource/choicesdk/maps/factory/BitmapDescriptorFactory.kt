package at.bluesource.choicesdk.maps.factory

import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.common.BitmapDescriptorFactory
import at.bluesource.choicesdk.maps.gms.GmsBitmapDescriptorFactory
import at.bluesource.choicesdk.maps.gms.GmsCameraUpdateFactory
import at.bluesource.choicesdk.maps.hms.HmsBitmapDescriptorFactory
import at.bluesource.choicesdk.maps.hms.HmsCameraUpdateFactory

/**
 * BitmapDescriptorFactory factory, uses [MobileServicesDetector] to get instance of [BitmapDescriptorFactory]
 * Automatically decides if GMS or HMS should be used. GMS is always preferred first.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsBitmapDescriptorFactory
 * @see HmsBitmapDescriptorFactory
 */
internal class BitmapDescriptorFactory {
    companion object {
        private const val EXCEPTION_MESSAGE = "Missing underlying GMS/HMS API."

        @Throws(UnsupportedOperationException::class)
        fun getBitmapDescriptorFactory(): BitmapDescriptorFactory {

            return when {
                MobileServicesDetector.isGmsAvailable() -> {
                    GmsBitmapDescriptorFactory.getInstance()
                }
                MobileServicesDetector.isHmsAvailable() -> {
                    HmsBitmapDescriptorFactory.getInstance()
                }
                else -> {
                    throw UnsupportedOperationException(EXCEPTION_MESSAGE)
                }
            }
        }
    }
}