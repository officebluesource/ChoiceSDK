package at.bluesource.choicesdk.core


/**
 * Class to detect the mobile service availability
 *
 * Returns always true if a build variant for the specific service is set
 *
 * @see com.google.android.gms.common.GoogleApiAvailability
 * @see com.huawei.hms.api.HuaweiApiAvailability
 */
class MobileServicesDetector {

    companion object {

        /**
         * Check if Google Play Services are available
         */
        fun isGmsAvailable(): Boolean {
            return com.google.android.gms.common.GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(ChoiceSdk.getContext()) == com.google.android.gms.common.ConnectionResult.SUCCESS
        }

        /**
         * Check if Huawei Mobile Services are available
         */
        fun isHmsAvailable(): Boolean {
            return com.huawei.hms.api.HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(ChoiceSdk.getContext()) == com.huawei.hms.api.ConnectionResult.SUCCESS
        }

        /**
         * Check of any mobile services are available
         */
        fun anyMobileServicesAvailable(): Boolean {
            return isGmsAvailable() || isHmsAvailable()
        }
    }
}