package at.bluesource.choicesdk.maps.factory

import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.common.options.TileOverlayOptions
import at.bluesource.choicesdk.maps.gms.GmsTileOverlayOptions
import at.bluesource.choicesdk.maps.hms.HmsTileOverlayOptions

/**
 * TileOverlayOptions factory, uses [MobileServicesDetector] to get instance of [TileOverlayOptions]
 * Automatically decides if GMS or HMS should be used. GMS is always preferred first.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsTileOverlayOptions
 * @see HmsTileOverlayOptions
 */
internal class TileOverlayOptionsFactory {
    companion object {
        @Throws(UnsupportedOperationException::class)
        fun getTileOverlayOptions(): TileOverlayOptions {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsTileOverlayOptions(com.google.android.gms.maps.model.TileOverlayOptions())
                MobileService.HMS -> HmsTileOverlayOptions(com.huawei.hms.maps.model.TileOverlayOptions())
            }
        }
    }
}