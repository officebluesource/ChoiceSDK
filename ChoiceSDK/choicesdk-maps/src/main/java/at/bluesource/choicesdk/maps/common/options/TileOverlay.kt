package at.bluesource.choicesdk.maps.common.options

import at.bluesource.choicesdk.maps.gms.GmsTileOverlay
import at.bluesource.choicesdk.maps.hms.HmsTileOverlay


interface TileOverlay {

    fun toGmsTileOverlay(): com.google.android.gms.maps.model.TileOverlay

    fun remove()

    companion object {

        internal fun create(overlay: com.google.android.gms.maps.model.TileOverlay): TileOverlay {
            return GmsTileOverlay(overlay)
        }

        internal fun create(overlay: com.huawei.hms.maps.model.TileOverlay): TileOverlay {
            return HmsTileOverlay(overlay)
        }
    }

}