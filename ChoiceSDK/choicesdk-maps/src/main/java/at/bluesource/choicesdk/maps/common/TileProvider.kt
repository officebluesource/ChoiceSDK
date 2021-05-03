package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.maps.gms.GmsTileProvider
import at.bluesource.choicesdk.maps.hms.HmsTileProvider

/**
 * An interface for a class that provides the tile images for a TileOverlay.
 *
 * Calls to methods in this interface might be made from multiple threads so implementations of this interface must be threadsafe
 *
 * @see com.google.android.gms.maps.model.TileProvider
 * @see com.huawei.hms.maps.model.TileProvider
 */
fun interface TileProvider {

    fun getTile(x: Int, y: Int, zoom: Int): Tile

    companion object {

        @JvmStatic
        fun noTile(): Tile = Tile.getFactory().create(-1, -1, null)

        @JvmStatic
        fun com.google.android.gms.maps.model.TileProvider.toChoiceTileProvider(): TileProvider {
            return GmsTileProvider(this@toChoiceTileProvider)
        }

        @JvmStatic
        fun com.huawei.hms.maps.model.TileProvider.toChoiceTileProvider(): TileProvider {
            return HmsTileProvider(this@toChoiceTileProvider)
        }

        fun TileProvider.toGmsTileProvider(): com.google.android.gms.maps.model.TileProvider? {
            return when (this) {
                is GmsTileProvider -> {
                    this.getTileProvider()
                }
                else -> null
            }
        }

        fun TileProvider.toHmsTileProvider(): com.huawei.hms.maps.model.TileProvider? {
            return when (this) {
                is HmsTileProvider -> {
                    this.getTileProvider()
                }
                else -> null
            }
        }
    }
}