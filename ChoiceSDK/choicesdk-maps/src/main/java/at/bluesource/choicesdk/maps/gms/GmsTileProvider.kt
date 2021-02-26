package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.Tile
import at.bluesource.choicesdk.maps.common.Tile.Companion.toChoiceTile
import at.bluesource.choicesdk.maps.common.TileProvider

/**
 * Wrapper class for gms version of TileProvider
 *
 * @property tileProvider gms TileProvider instance
 * @see com.google.android.gms.maps.model.TileProvider
 */
internal class GmsTileProvider(private val tileProvider: com.google.android.gms.maps.model.TileProvider) :
    TileProvider {
    override fun getTile(x: Int, y: Int, zoom: Int): Tile {
        return tileProvider.getTile(x, y, zoom).toChoiceTile()
    }

    internal fun getTileProvider(): com.google.android.gms.maps.model.TileProvider {
        return tileProvider
    }
}