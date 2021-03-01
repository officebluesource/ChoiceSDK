package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.Tile

class GmsTileFactory : Tile.Factory {
    override fun create(width: Int, height: Int, data: ByteArray?): Tile {
        return GmsTile(width, height, data)
    }
}