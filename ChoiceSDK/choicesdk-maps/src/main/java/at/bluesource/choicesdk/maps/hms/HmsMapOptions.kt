package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.MapOptions
import com.huawei.hms.maps.HuaweiMapOptions


internal class HmsMapOptions : MapOptions {
    private val mapOptions = HuaweiMapOptions()

    override val liteMode: Boolean?
        get() = mapOptions.liteMode

    override val mapToolbarEnabled: Boolean?
        get() = mapOptions.mapToolbarEnabled

    override fun liteMode(enable: Boolean) {
        mapOptions.liteMode(enable)
    }

    override fun mapToolbarEnabled(enable: Boolean) {
        mapOptions.mapToolbarEnabled(enable)
    }

}