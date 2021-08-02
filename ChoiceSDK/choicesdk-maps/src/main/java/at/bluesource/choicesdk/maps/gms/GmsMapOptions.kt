package at.bluesource.choicesdk.maps.gms

import android.os.Parcelable
import at.bluesource.choicesdk.maps.common.MapOptions
import com.google.android.gms.maps.GoogleMapOptions
import kotlinx.parcelize.Parcelize


@Parcelize
internal class GmsMapOptions(
    private val mapOptions: GoogleMapOptions = GoogleMapOptions()
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