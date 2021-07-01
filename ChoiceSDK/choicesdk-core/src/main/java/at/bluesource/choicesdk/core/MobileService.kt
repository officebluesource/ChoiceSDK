package at.bluesource.choicesdk.core

enum class MobileService {
    GMS, HMS;

    fun isAvailable(): Boolean {
        return when (this) {
            GMS -> com.google.android.gms.common.GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(ChoiceSdk.getContext()) == com.google.android.gms.common.ConnectionResult.SUCCESS
            HMS -> com.huawei.hms.api.HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(ChoiceSdk.getContext()) == com.huawei.hms.api.ConnectionResult.SUCCESS
        }
    }
}