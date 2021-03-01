package at.bluesource.choicesdk.location.factory

import android.app.Activity
import android.content.Context
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.location.common.FusedLocationProviderClient
import at.bluesource.choicesdk.location.gms.GmsFusedLocationProviderClient
import at.bluesource.choicesdk.location.hms.HmsFusedLocationProviderClient
import com.google.android.gms.location.LocationServices

/**
 * FusedLocationProvider factory, uses [MobileServicesDetector] to get instance of [FusedLocationProviderClient]
 * Automatically decides if GMS or HMS should be used. GMS is always preferred first.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsFusedLocationProviderClient
 * @see HmsFusedLocationProviderClient
 */
class FusedLocationProviderFactory {
    companion object {
        private const val EXCEPTION_MESSAGE = "Missing underlying GMS/HMS API."

        @Throws(UnsupportedOperationException::class)
        fun getFusedLocationProviderClient(context: Context): FusedLocationProviderClient {

            return when {
                MobileServicesDetector.isGmsAvailable() -> {
                    GmsFusedLocationProviderClient(
                        LocationServices.getFusedLocationProviderClient(
                            context
                        )
                    )
                }
                MobileServicesDetector.isHmsAvailable() -> {
                    HmsFusedLocationProviderClient(
                        com.huawei.hms.location.LocationServices.getFusedLocationProviderClient(
                            context
                        )
                    )
                }
                else -> {
                    throw UnsupportedOperationException(EXCEPTION_MESSAGE)
                }
            }
        }

        @Throws(UnsupportedOperationException::class)
        fun getFusedLocationProviderClient(activity: Activity): FusedLocationProviderClient {

            return when {
                MobileServicesDetector.isGmsAvailable() -> {
                    GmsFusedLocationProviderClient(
                        LocationServices.getFusedLocationProviderClient(
                            activity
                        )
                    )
                }
                MobileServicesDetector.isHmsAvailable() -> {
                    HmsFusedLocationProviderClient(
                        com.huawei.hms.location.LocationServices.getFusedLocationProviderClient(
                            activity
                        )
                    )
                }
                else -> {
                    throw UnsupportedOperationException(EXCEPTION_MESSAGE)
                }
            }
        }
    }
}