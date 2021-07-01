package at.bluesource.choicesdk.location.factory

import android.app.Activity
import android.content.Context
import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.location.common.SettingsClient
import at.bluesource.choicesdk.location.gms.GmsSettingsClient
import at.bluesource.choicesdk.location.hms.HmsSettingsClient
import com.google.android.gms.location.LocationServices

/**
 * SettingsClient factory, uses [MobileServicesDetector] to get instance of [SettingsClient]
 * Automatically decides if GMS or HMS should be used. GMS is always preferred first.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsSettingsClient
 * @see HmsSettingsClient
 */
class SettingsClientFactory {
    companion object {

        @Throws(UnsupportedOperationException::class)
        fun getSettingsClient(context: Context): SettingsClient {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsSettingsClient(
                    LocationServices.getSettingsClient(
                        context
                    )
                )
                MobileService.HMS -> HmsSettingsClient(
                    com.huawei.hms.location.LocationServices.getSettingsClient(
                        context
                    )
                )
            }
        }

        @Throws(UnsupportedOperationException::class)
        fun getSettingsClient(activity: Activity): SettingsClient {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsSettingsClient(
                    LocationServices.getSettingsClient(
                        activity
                    )
                )
                MobileService.HMS -> HmsSettingsClient(
                    com.huawei.hms.location.LocationServices.getSettingsClient(
                        activity
                    )
                )
            }
        }
    }
}