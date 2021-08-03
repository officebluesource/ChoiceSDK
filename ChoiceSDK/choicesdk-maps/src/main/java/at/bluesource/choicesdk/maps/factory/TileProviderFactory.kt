package at.bluesource.choicesdk.maps.factory

import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.common.Tile
import at.bluesource.choicesdk.maps.common.TileProvider
import at.bluesource.choicesdk.maps.gms.GmsTileProvider
import at.bluesource.choicesdk.maps.hms.HmsTileProvider

/**
 * TileProvider factory, uses [MobileServicesDetector] to get instance of [TileProvider]
 * Automatically decides if GMS or HMS should be used.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsTileProvider
 * @see HmsTileProvider
 */
internal class TileProviderFactory {
    companion object {
        @Throws(UnsupportedOperationException::class)
        fun getTileProvider(getTile: (x: Int, y: Int, zoom: Int) -> Tile?): TileProvider {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsTileProvider { x, y, zoom ->
                    getTile.invoke(x, y, zoom)?.let { com.google.android.gms.maps.model.Tile(it.width, it.height, it.data) }
                }

                MobileService.HMS -> HmsTileProvider { x, y, zoom ->
                    getTile.invoke(x, y, zoom)?.let { com.huawei.hms.maps.model.Tile(it.width, it.height, it.data) }
                }
            }
        }
    }
}