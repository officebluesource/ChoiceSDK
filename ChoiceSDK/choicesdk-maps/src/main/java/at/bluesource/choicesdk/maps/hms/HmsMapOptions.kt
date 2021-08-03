package at.bluesource.choicesdk.maps.hms

import android.os.Parcelable
import at.bluesource.choicesdk.maps.common.MapOptions
import com.huawei.hms.maps.HuaweiMapOptions
import kotlinx.parcelize.Parcelize


@Parcelize
internal class HmsMapOptions(
    private val mapOptions: HuaweiMapOptions = HuaweiMapOptions()
) : MapOptions, Parcelable {

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