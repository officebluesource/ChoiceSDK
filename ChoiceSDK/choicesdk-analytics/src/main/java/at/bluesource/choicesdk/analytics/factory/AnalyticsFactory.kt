package at.bluesource.choicesdk.analytics.factory

import android.app.Activity
import android.content.Context
import androidx.annotation.RequiresPermission
import at.bluesource.choicesdk.analytics.common.Analytics
import at.bluesource.choicesdk.analytics.gms.FirebaseAnalytics
import at.bluesource.choicesdk.analytics.hms.HuaweiAnalytics
import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector

/**
 * Analytics factory, uses [MobileServicesDetector] to get instance of [Analytics]
 * Automatically decides if GMS or HMS should be used. GMS is always preferred first.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see at.bluesource.choicesdk.analytics.gms.FirebaseAnalytics
 * @see at.bluesource.choicesdk.analytics.hms.HuaweiAnalytics
 */
class AnalyticsFactory {
    companion object {

        @Throws(UnsupportedOperationException::class)
        @RequiresPermission(allOf = ["android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET", "android.permission.WAKE_LOCK"])
        fun getAnalytics(context: Context): Analytics {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> FirebaseAnalytics.getInstance(context)
                MobileService.HMS -> HuaweiAnalytics.getInstance(context)
            }
        }

        @Throws(UnsupportedOperationException::class)
        @RequiresPermission(allOf = ["android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET", "android.permission.WAKE_LOCK"])
        fun getAnalytics(activity: Activity): Analytics {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> FirebaseAnalytics.getInstance(activity)
                MobileService.HMS -> HuaweiAnalytics.getInstance(activity)
            }
        }
    }
}