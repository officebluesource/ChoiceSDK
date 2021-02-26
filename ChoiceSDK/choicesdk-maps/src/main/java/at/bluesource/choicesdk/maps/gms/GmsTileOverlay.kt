package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.options.TileOverlay

internal class GmsTileOverlay(private val overlay: com.google.android.gms.maps.model.TileOverlay) : TileOverlay {
    override fun toGmsTileOverlay(): com.google.android.gms.maps.model.TileOverlay {
        return overlay
    }

    override fun remove() {
        overlay.remove()
    }
}