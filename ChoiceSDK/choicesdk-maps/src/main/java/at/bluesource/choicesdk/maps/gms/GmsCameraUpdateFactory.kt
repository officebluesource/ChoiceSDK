package at.bluesource.choicesdk.maps.gms

import android.graphics.Point
import at.bluesource.choicesdk.maps.common.*
import at.bluesource.choicesdk.maps.common.CameraPosition.Companion.toGmsCameraPosition
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toGmsLatLngBounds

/**
 * CameraUpdate factory to get instance of [CameraUpdate]
 *
 * @see com.google.android.gms.maps.CameraUpdateFactory
 * @see com.google.android.gms.maps.model.CameraPosition
 */
internal class GmsCameraUpdateFactory private constructor() : CameraUpdateFactory {

    override fun newCameraPosition(cameraPosition: CameraPosition): CameraUpdate {
        return CameraUpdate.GmsUpdate(
                com.google.android.gms.maps.CameraUpdateFactory.newCameraPosition(
                        cameraPosition.toGmsCameraPosition()
                )
        )
    }

    override fun newLatLng(latLng: LatLng): CameraUpdate {
        return CameraUpdate.GmsUpdate(
                com.google.android.gms.maps.CameraUpdateFactory.newLatLng(
                        com.google.android.gms.maps.model.LatLng(latLng.latitude, latLng.longitude)
                )
        )
    }

    override fun newLatLngBounds(bounds: LatLngBounds, padding: Int): CameraUpdate {
        return CameraUpdate.GmsUpdate(com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(bounds.toGmsLatLngBounds(), padding))
    }

    override fun newLatLngBounds(
            latLngNorthEast: LatLng,
            latLngSouthWest: LatLng,
            width: Int,
            height: Int,
            padding: Int
    ): CameraUpdate {
        return CameraUpdate.GmsUpdate(
                com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(
                        com.google.android.gms.maps.model.LatLngBounds(
                                com.google.android.gms.maps.model.LatLng(
                                        latLngNorthEast.latitude,
                                        latLngNorthEast.longitude
                                ),
                                com.google.android.gms.maps.model.LatLng(
                                        latLngSouthWest.latitude,
                                        latLngSouthWest.longitude
                                )
                        ),
                        width,
                        height,
                        padding
                )
        )
    }

    override fun newLatLngBounds(
            latLngNorthEast: LatLng,
            latLngSouthWest: LatLng,
            padding: Int
    ): CameraUpdate {
        return CameraUpdate.GmsUpdate(
                com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(
                        com.google.android.gms.maps.model.LatLngBounds(
                                com.google.android.gms.maps.model.LatLng(
                                        latLngNorthEast.latitude,
                                        latLngNorthEast.longitude
                                ),
                                com.google.android.gms.maps.model.LatLng(
                                        latLngSouthWest.latitude,
                                        latLngSouthWest.longitude
                                )
                        ),
                        padding
                )
        )
    }

    override fun newLatLngZoom(
            latLng: LatLng,
            zoom: Float
    ): CameraUpdate {
        return CameraUpdate.GmsUpdate(
                com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
                        com.google.android.gms.maps.model.LatLng(latLng.latitude, latLng.longitude), zoom
                )
        )
    }

    override fun scrollBy(xPixel: Float, yPixel: Float): CameraUpdate {
        return CameraUpdate.GmsUpdate(
                com.google.android.gms.maps.CameraUpdateFactory.scrollBy(
                        xPixel,
                        yPixel
                )
        )
    }

    override fun zoomBy(amount: Float, focus: Point): CameraUpdate {
        return CameraUpdate.GmsUpdate(
                com.google.android.gms.maps.CameraUpdateFactory.zoomBy(
                        amount,
                        focus
                )
        )
    }

    override fun zoomBy(amount: Float): CameraUpdate {
        return CameraUpdate.GmsUpdate(com.google.android.gms.maps.CameraUpdateFactory.zoomBy(amount))
    }

    override fun zoomIn(): CameraUpdate {
        return CameraUpdate.GmsUpdate(com.google.android.gms.maps.CameraUpdateFactory.zoomIn())
    }

    override fun zoomOut(): CameraUpdate {
        return CameraUpdate.GmsUpdate(com.google.android.gms.maps.CameraUpdateFactory.zoomOut())
    }

    override fun zoomTo(zoom: Float): CameraUpdate {
        return CameraUpdate.GmsUpdate(com.google.android.gms.maps.CameraUpdateFactory.zoomTo(zoom))
    }

    companion object {
        @Volatile
        private var INSTANCE: GmsCameraUpdateFactory? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        fun getInstance(): GmsCameraUpdateFactory {
            return INSTANCE
                    ?: synchronized(this) {
                        GmsCameraUpdateFactory().also {
                            INSTANCE = it
                        }
                    }
        }
    }
}
