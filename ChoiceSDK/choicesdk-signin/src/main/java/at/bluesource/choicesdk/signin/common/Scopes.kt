package at.bluesource.choicesdk.signin.common

import at.bluesource.choicesdk.core.MobileServicesDetector

/**
 * Available common scopes for both hms and gms,
 * gms [com.google.android.gms.common.Scopes] has many more
 */
sealed class Scopes {
    // hms: https://developer.huawei.com/consumer/en/doc/HMSCore-References-V5/scope-0000001050123083-V5
    // gms: https://developers.google.com/android/reference/com/google/android/gms/common/Scopes?hl=en#EMAIL
    companion object {
        const val PROFILE = "profile"
        const val OPEN_ID = "openid"
        const val EMAIL = "email"
        val GAMES = when {
            MobileServicesDetector.isGmsAvailable() -> {
                "https://www.googleapis.com/auth/games"
            }
            MobileServicesDetector.isHmsAvailable() -> {
                "https://www.huawei.com/auth/games"
            }
            else -> ""
        }
    }
}