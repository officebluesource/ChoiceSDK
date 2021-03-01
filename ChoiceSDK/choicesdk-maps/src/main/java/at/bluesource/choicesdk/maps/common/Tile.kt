package at.bluesource.choicesdk.maps.common

import android.os.Parcel
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.gms.GmsTile
import at.bluesource.choicesdk.maps.gms.GmsTileFactory
import at.bluesource.choicesdk.maps.hms.HmsTile
import at.bluesource.choicesdk.maps.hms.HmsTileFactory

/**
 * Abstract base class for hms and gms Tile
 *
 * @property data A byte array containing the image data.
 * @property height The height of the image encoded by data in pixels.
 * @property width The width of the image encoded by data in pixels.
 */
abstract class Tile(val width: Int, val height: Int, val data: ByteArray?) {
    abstract fun writeToParcel(out: Parcel, flags: Int)

    interface Factory {
        fun create(width: Int, height: Int, data: ByteArray?): Tile
    }

    companion object {

        @JvmStatic
        fun getFactory(): Factory {
            return when {
                MobileServicesDetector.isGmsAvailable() -> GmsTileFactory()
                MobileServicesDetector.isHmsAvailable() -> HmsTileFactory()
                else -> throw IllegalStateException("Neither GMS nor HMS services are available.")
            }
        }

        internal fun com.google.android.gms.maps.model.Tile.toChoiceTile(): Tile {
            return GmsTile(this.width, this.height, this.data)
        }

        internal fun com.huawei.hms.maps.model.Tile.toChoiceTile(): Tile {
            return HmsTile(this.width, this.height, this.data)
        }
    }
}