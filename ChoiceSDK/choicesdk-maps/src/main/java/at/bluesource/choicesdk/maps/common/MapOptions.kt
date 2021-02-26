package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.gms.GmsMapOptionsFactory
import at.bluesource.choicesdk.maps.hms.HmsMapOptionsFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.huawei.hms.maps.HuaweiMapOptions

interface MapOptions {

    val liteMode: Boolean
    val mapToolbarEnabled: Boolean

    fun liteMode(enable: Boolean)
    fun mapToolbarEnabled(enable: Boolean)

    interface Factory {
        fun create(): MapOptions
    }

    companion object {

        @JvmStatic
        fun getFactory(): Factory {
            return when {
                MobileServicesDetector.isGmsAvailable() -> GmsMapOptionsFactory()
                MobileServicesDetector.isHmsAvailable() -> HmsMapOptionsFactory()
                else -> throw IllegalStateException("Neither GMS nor HMS services are available.")
            }
        }

        fun MapOptions.toGmsMapOptions(): GoogleMapOptions {
            return GoogleMapOptions()
                .liteMode(liteMode)
                .mapToolbarEnabled(mapToolbarEnabled)
        }

        fun MapOptions.toHmsMapOptions(): HuaweiMapOptions {
            return HuaweiMapOptions()
                .liteMode(liteMode)
                .mapToolbarEnabled(mapToolbarEnabled)
        }
    }

}