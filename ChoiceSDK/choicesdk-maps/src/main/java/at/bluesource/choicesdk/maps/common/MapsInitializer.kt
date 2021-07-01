package at.bluesource.choicesdk.maps.common

import android.content.Context
import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector

class MapsInitializer {

    companion object {
        @JvmStatic
        fun initialize(context: Context) {
            when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> com.google.android.gms.maps.MapsInitializer.initialize(context)
                MobileService.HMS -> com.huawei.hms.maps.MapsInitializer.initialize(context)
            }
        }
    }

}