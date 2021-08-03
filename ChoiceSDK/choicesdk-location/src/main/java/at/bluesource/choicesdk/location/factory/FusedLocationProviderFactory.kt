package at.bluesource.choicesdk.location.factory

import android.app.Activity
import android.content.Context
import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.location.common.FusedLocationProviderClient
import at.bluesource.choicesdk.location.gms.GmsFusedLocationProviderClient
import at.bluesource.choicesdk.location.hms.HmsFusedLocationProviderClient

/**
 * FusedLocationProvider factory, uses [MobileServicesDetector] to get instance of [FusedLocationProviderClient]
 * Automatically decides if GMS or HMS should be used.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsFusedLocationProviderClient
 * @see HmsFusedLocationProviderClient
 */
class FusedLocationProviderFactory {
    companion object {

        @Throws(UnsupportedOperationException::class)
        fun getFusedLocationProviderClient(context: Context): FusedLocationProviderClient {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsFusedLocationProviderClient(
                    com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(context)
                )
                MobileService.HMS -> HmsFusedLocationProviderClient(
                    com.huawei.hms.location.LocationServices.getFusedLocationProviderClient(context)
                )
            }
        }

        @Throws(UnsupportedOperationException::class)
        fun getFusedLocationProviderClient(activity: Activity): FusedLocationProviderClient {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsFusedLocationProviderClient(
                    com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(activity)
                )
                MobileService.HMS -> HmsFusedLocationProviderClient(
                    com.huawei.hms.location.LocationServices.getFusedLocationProviderClient(activity)
                )
            }
        }
    }
}