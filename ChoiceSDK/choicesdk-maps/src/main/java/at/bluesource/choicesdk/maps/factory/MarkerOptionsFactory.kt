package at.bluesource.choicesdk.maps.factory

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
        private const val EXCEPTION_MESSAGE = "Missing underlying GMS/HMS API."

        @Throws(UnsupportedOperationException::class)
        fun getMarkerOptions(): MarkerOptions {

            return when {
                MobileServicesDetector.isGmsAvailable() -> {
                    GmsMarkerOptions(com.google.android.gms.maps.model.MarkerOptions())
                }
                MobileServicesDetector.isHmsAvailable() -> {
                    HmsMarkerOptions(com.huawei.hms.maps.model.MarkerOptions())
                }
                else -> {
                    throw UnsupportedOperationException(EXCEPTION_MESSAGE)
                }
            }
        }
    }
}