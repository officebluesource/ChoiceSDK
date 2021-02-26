package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.MapOptions
import com.google.android.gms.maps.GoogleMapOptions


internal class GmsMapOptions : MapOptions {
    private val mapOptions = GoogleMapOptions()

    override val liteMode: Boolean
        get() = mapOptions.liteMode

    override val mapToolbarEnabled: Boolean
        get() = mapOptions.mapToolbarEnabled

    override fun liteMode(enable: Boolean) {
        mapOptions.liteMode(enable)
    }

    override fun mapToolbarEnabled(enable: Boolean) {
        mapOptions.mapToolbarEnabled(enable)
    }

}