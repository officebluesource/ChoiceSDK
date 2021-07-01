package at.bluesource.choicesdk.maps.common.options

import android.os.Parcel
import at.bluesource.choicesdk.maps.common.TileProvider
import at.bluesource.choicesdk.maps.common.TileProvider.Companion.toChoiceTileProvider
import at.bluesource.choicesdk.maps.factory.TileOverlayOptionsFactory
import at.bluesource.choicesdk.maps.gms.GmsTileOverlayOptions
import at.bluesource.choicesdk.maps.hms.HmsTileOverlayOptions

/**
 * Common interface for gms hms TileOverlayOptions
 *
 * Defines options for a TileOverlay.
 *
 * @see com.google.android.gms.maps.model.TileOverlayOptions
 * @see com.huawei.hms.maps.model.TileOverlayOptions
 */
interface TileOverlayOptions {
    fun fadeIn(fadeIn: Boolean): TileOverlayOptions
    fun getFadeIn(): Boolean
    fun getTileProvider(): TileProvider?
    fun getTransparency(): Float
    fun getZIndex(): Float
    fun isVisible(): Boolean
    fun tileProvider(tileProvider: TileProvider?): TileOverlayOptions
    fun transparency(transparency: Float): TileOverlayOptions
    fun visible(visible: Boolean): TileOverlayOptions
    fun writeToParcel(out: Parcel, flags: Int)
    fun zIndex(zIndex: Float): TileOverlayOptions

    companion object {

        fun create(): TileOverlayOptions {
            return TileOverlayOptionsFactory.getTileOverlayOptions()
        }

        @JvmStatic
        fun com.google.android.gms.maps.model.TileOverlayOptions.toChoice(): TileOverlayOptions {
            return create()
                    .fadeIn(this.fadeIn)
                    .tileProvider(this.tileProvider?.toChoiceTileProvider())
                    .transparency(this.transparency)
                    .visible(this.isVisible)
                    .zIndex(this.zIndex)
        }

        internal fun TileOverlayOptions.toGmsTileOverlayOptions(): com.google.android.gms.maps.model.TileOverlayOptions? {
            return when (this) {
                is GmsTileOverlayOptions -> {
                    this.getTileOverlayOptions()
                }
                else -> null
            }
        }

        internal fun TileOverlayOptions.toHmsTileOverlayOptions(): com.huawei.hms.maps.model.TileOverlayOptions? {
            return when (this) {
                is HmsTileOverlayOptions -> {
                    this.getTileOverlayOptions()
                }
                else -> null
            }
        }
    }
}