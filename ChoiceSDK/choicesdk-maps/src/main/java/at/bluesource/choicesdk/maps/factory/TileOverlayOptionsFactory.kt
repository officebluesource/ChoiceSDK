package at.bluesource.choicesdk.maps.factory

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
        private const val EXCEPTION_MESSAGE = "Missing underlying GMS/HMS API."

        @Throws(UnsupportedOperationException::class)
        fun getTileOverlayOptions(): TileOverlayOptions {

            return when {
                MobileServicesDetector.isGmsAvailable() -> {
                    GmsTileOverlayOptions(com.google.android.gms.maps.model.TileOverlayOptions())
                }
                MobileServicesDetector.isHmsAvailable() -> {
                    HmsTileOverlayOptions(com.huawei.hms.maps.model.TileOverlayOptions())
                }
                else -> {
                    throw UnsupportedOperationException(EXCEPTION_MESSAGE)
                }
            }
        }
    }
}