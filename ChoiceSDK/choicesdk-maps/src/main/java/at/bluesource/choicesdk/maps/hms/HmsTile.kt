package at.bluesource.choicesdk.maps.hms

import android.os.Parcel
import at.bluesource.choicesdk.maps.common.Tile

/**
 * Wrapper for hms version of Tile
 *
 * @constructor create a hms tile
 *
 * @property data A byte array containing the image data.
 * @property height The height of the image encoded by data in pixels.
 * @property width The width of the image encoded by data in pixels.
 */
internal class HmsTile(width: Int, height: Int, data: ByteArray?) : Tile(width, height, data) {
    private val tile = com.huawei.hms.maps.model.Tile(width, height, data)

    override fun writeToParcel(out: Parcel, flags: Int) {
        tile.writeToParcel(out, flags)
    }
}