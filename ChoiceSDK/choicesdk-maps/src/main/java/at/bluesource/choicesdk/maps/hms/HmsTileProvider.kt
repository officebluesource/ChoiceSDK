package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.Tile
import at.bluesource.choicesdk.maps.common.Tile.Companion.toChoiceTile
import at.bluesource.choicesdk.maps.common.TileProvider

/**
 * Wrapper class for hms version of TileProvider
 *
 * @property tileProvider hms TileProvider instance
 * @see com.huawei.hms.maps.model.TileProvider
 */
internal class HmsTileProvider(private val tileProvider: com.huawei.hms.maps.model.TileProvider) :
    TileProvider {

    override fun getTile(x: Int, y: Int, zoom: Int): Tile {
        return tileProvider.getTile(x, y, zoom).toChoiceTile()
    }

    internal fun getTileProvider(): com.huawei.hms.maps.model.TileProvider {
        return tileProvider
    }
}