package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.Tile

class HmsTileFactory : Tile.Factory {
    override fun create(width: Int, height: Int, data: ByteArray?): Tile {
        return HmsTile(width, height, data)
    }
}