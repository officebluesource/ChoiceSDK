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
         * Checks which mobile service is available. This check considers the priorities set during [ChoiceSdk] initialization.
         * @throws UnsupportedOperationException if no mobile service is available.
         */
        @Throws(UnsupportedOperationException::class)
        fun getAvailableMobileService(): MobileService {
            ChoiceSdk.mobileServicePriorities.forEach { mobileService ->
                if (mobileService.isAvailable()) {
                    return mobileService
                }
            }

            throw UnsupportedOperationException("Missing underlying GMS/HMS API.")
        }

        /**
         * Check if Google Play Services are available
         */
        fun isGmsAvailable(): Boolean {
            return MobileService.GMS.isAvailable()
        }

        /**
         * Check if Huawei Mobile Services are available
         */
        fun isHmsAvailable(): Boolean {
            return MobileService.HMS.isAvailable()
        }

        /**
         * Check of any mobile services are available
         */
        fun anyMobileServicesAvailable(): Boolean {
            return try {
                getAvailableMobileService()
                true
            } catch (e: UnsupportedOperationException) {
                false
            }
        }
    }
}