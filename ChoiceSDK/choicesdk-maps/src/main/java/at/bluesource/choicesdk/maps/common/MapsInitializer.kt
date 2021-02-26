package at.bluesource.choicesdk.maps.common

import android.content.Context
import at.bluesource.choicesdk.core.MobileServicesDetector

class MapsInitializer {

    companion object {
        @JvmStatic
        fun initialize(context: Context) {
            when {
                MobileServicesDetector.isGmsAvailable() -> com.google.android.gms.maps.MapsInitializer.initialize(context)
                MobileServicesDetector.isHmsAvailable() -> com.huawei.hms.maps.MapsInitializer.initialize(context)
                else -> throw IllegalStateException("Neither GMS nor HMS services are available.")
            }
        }
    }

}