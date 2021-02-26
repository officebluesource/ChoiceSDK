package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.options.TileOverlay

internal class HmsTileOverlay(private val overlay: com.huawei.hms.maps.model.TileOverlay) : TileOverlay {

    override fun toGmsTileOverlay(): com.google.android.gms.maps.model.TileOverlay {
        TODO("Conversion of HMS TileOverlay to GMS TileOverlay is currently not supported or implemented.")
    }

    override fun remove() {
        overlay.remove()
    }
}