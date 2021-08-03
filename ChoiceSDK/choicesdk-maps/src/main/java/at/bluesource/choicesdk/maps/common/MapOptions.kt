package at.bluesource.choicesdk.maps.common

import android.os.Parcelable
import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.gms.GmsMapOptionsFactory
import at.bluesource.choicesdk.maps.hms.HmsMapOptionsFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.huawei.hms.maps.HuaweiMapOptions

interface MapOptions : Parcelable {

    val liteMode: Boolean?
    val mapToolbarEnabled: Boolean?

    fun liteMode(enable: Boolean)
    fun mapToolbarEnabled(enable: Boolean)

    interface Factory {
        fun create(): MapOptions
    }

    companion object {

        @JvmStatic
        fun getFactory(): Factory {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsMapOptionsFactory()
                MobileService.HMS -> HmsMapOptionsFactory()
            }
        }

        fun MapOptions.toGmsMapOptions(): GoogleMapOptions {
            val options = GoogleMapOptions()
            liteMode?.let { options.liteMode(it) }
            mapToolbarEnabled?.let { options.mapToolbarEnabled(it) }
            return options
        }

        fun MapOptions.toHmsMapOptions(): HuaweiMapOptions {
            val options = HuaweiMapOptions()
            liteMode?.let { options.liteMode(it) }
            mapToolbarEnabled?.let { options.mapToolbarEnabled(it) }
            return options
        }
    }

}