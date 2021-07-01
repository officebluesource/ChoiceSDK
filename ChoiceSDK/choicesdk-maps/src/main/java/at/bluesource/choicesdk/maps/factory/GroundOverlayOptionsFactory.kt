package at.bluesource.choicesdk.maps.factory

import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.common.options.GroundOverlayOptions
import at.bluesource.choicesdk.maps.gms.GmsGroundOverlayOptions
import at.bluesource.choicesdk.maps.hms.HmsGroundOverlayOptions

/**
 * GroundOverlayOptions factory, uses [MobileServicesDetector] to get instance of [GroundOverlayOptions]
 * Automatically decides if GMS or HMS should be used. GMS is always preferred first.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsGroundOverlayOptions
 * @see HmsGroundOverlayOptions
 */
internal class GroundOverlayOptionsFactory {
    companion object {
        @Throws(UnsupportedOperationException::class)
        fun getGroundOverlayOptions(): GroundOverlayOptions {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsGroundOverlayOptions(com.google.android.gms.maps.model.GroundOverlayOptions())
                MobileService.HMS -> HmsGroundOverlayOptions(com.huawei.hms.maps.model.GroundOverlayOptions())
            }
        }
    }
}
