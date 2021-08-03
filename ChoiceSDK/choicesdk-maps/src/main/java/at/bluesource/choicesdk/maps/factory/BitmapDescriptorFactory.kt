package at.bluesource.choicesdk.maps.factory

import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.common.BitmapDescriptorFactory
import at.bluesource.choicesdk.maps.gms.GmsBitmapDescriptorFactory
import at.bluesource.choicesdk.maps.hms.HmsBitmapDescriptorFactory

/**
 * BitmapDescriptorFactory factory, uses [MobileServicesDetector] to get instance of [BitmapDescriptorFactory]
 * Automatically decides if GMS or HMS should be used.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsBitmapDescriptorFactory
 * @see HmsBitmapDescriptorFactory
 */
internal class BitmapDescriptorFactory {
    companion object {
        @Throws(UnsupportedOperationException::class)
        fun getBitmapDescriptorFactory(): BitmapDescriptorFactory {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsBitmapDescriptorFactory.getInstance()
                MobileService.HMS -> HmsBitmapDescriptorFactory.getInstance()
            }
        }
    }
}