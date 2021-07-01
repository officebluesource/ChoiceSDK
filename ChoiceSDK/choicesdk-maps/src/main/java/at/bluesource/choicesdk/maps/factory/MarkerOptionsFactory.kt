package at.bluesource.choicesdk.maps.factory

import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.common.options.MarkerOptions
import at.bluesource.choicesdk.maps.gms.GmsMarkerOptions
import at.bluesource.choicesdk.maps.hms.HmsMarkerOptions

/**
 * MarkerOptions factory, uses [MobileServicesDetector] to get instance of [MarkerOptions]
 * Automatically decides if GMS or HMS should be used. GMS is always preferred first.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsMarkerOptions
 * @see HmsMarkerOptions
 */
internal class MarkerOptionsFactory {
    companion object {
        @Throws(UnsupportedOperationException::class)
        fun getMarkerOptions(): MarkerOptions {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsMarkerOptions(com.google.android.gms.maps.model.MarkerOptions())
                MobileService.HMS -> HmsMarkerOptions(com.huawei.hms.maps.model.MarkerOptions())
            }
        }
    }
}