package at.bluesource.choicesdk.maps.hms

import android.graphics.Point
import at.bluesource.choicesdk.maps.common.CameraPosition
import at.bluesource.choicesdk.maps.common.CameraPosition.Companion.toHmsCameraPosition
import at.bluesource.choicesdk.maps.common.CameraUpdate
import at.bluesource.choicesdk.maps.common.CameraUpdate.Companion.toChoice
import at.bluesource.choicesdk.maps.common.CameraUpdateFactory
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toHmsLatLngBounds
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.LatLngBounds

/**
 * CameraUpdate factory to get instance of [CameraUpdate]
 *
 * @see com.huawei.hms.maps.CameraUpdateFactory
 * @see com.huawei.hms.maps.model.CameraPosition
 */
internal class HmsCameraUpdateFactory private constructor() : CameraUpdateFactory {
    override fun newCameraPosition(cameraPosition: CameraPosition): CameraUpdate {
        return CameraUpdate.HmsUpdate(
                com.huawei.hms.maps.CameraUpdateFactory.newCameraPosition(
                        cameraPosition.toHmsCameraPosition()
                )
        )
    }

    override fun newLatLng(latLng: at.bluesource.choicesdk.maps.common.LatLng): CameraUpdate {
        return CameraUpdate.HmsUpdate(
                com.huawei.hms.maps.CameraUpdateFactory.newLatLng(
                        LatLng(latLng.latitude, latLng.longitude)
                )
        )
    }

    override fun newLatLngBounds(bounds: at.bluesource.choicesdk.maps.common.LatLngBounds, padding: Int): CameraUpdate {

        val update = com.huawei.hms.maps.CameraUpdateFactory.newLatLngBounds(bounds.toHmsLatLngBounds(), padding)
        return update.toChoice()
    }

    override fun newLatLngBounds(
            latLngNorthEast: at.bluesource.choicesdk.maps.common.LatLng,
            latLngSouthWest: at.bluesource.choicesdk.maps.common.LatLng,
            width: Int,
            height: Int,
            padding: Int
    ): CameraUpdate {
        return CameraUpdate.HmsUpdate(
                com.huawei.hms.maps.CameraUpdateFactory.newLatLngBounds(
                        LatLngBounds(
                                LatLng(latLngNorthEast.latitude, latLngNorthEast.longitude),
                                LatLng(latLngSouthWest.latitude, latLngSouthWest.longitude)
                        ),
                        width,
                        height,
                        padding
                )
        )
    }

    override fun newLatLngBounds(
            latLngNorthEast: at.bluesource.choicesdk.maps.common.LatLng,
            latLngSouthWest: at.bluesource.choicesdk.maps.common.LatLng,
            padding: Int
    ): CameraUpdate {
        return CameraUpdate.HmsUpdate(
                com.huawei.hms.maps.CameraUpdateFactory.newLatLngBounds(
                        LatLngBounds(
                                LatLng(latLngNorthEast.latitude, latLngNorthEast.longitude),
                                LatLng(latLngSouthWest.latitude, latLngSouthWest.longitude)
                        ),
                        padding
                )
        )
    }

    override fun newLatLngZoom(
            latLng: at.bluesource.choicesdk.maps.common.LatLng,
            zoom: Float
    ): CameraUpdate {
        return CameraUpdate.HmsUpdate(
                com.huawei.hms.maps.CameraUpdateFactory.newLatLngZoom(
                        LatLng(latLng.latitude, latLng.longitude), zoom
                )
        )
    }

    override fun scrollBy(xPixel: Float, yPixel: Float): CameraUpdate {
        return CameraUpdate.HmsUpdate(
                com.huawei.hms.maps.CameraUpdateFactory.scrollBy(
                        xPixel,
                        yPixel
                )
        )
    }

    override fun zoomBy(amount: Float, focus: Point): CameraUpdate {
        return CameraUpdate.HmsUpdate(
                com.huawei.hms.maps.CameraUpdateFactory.zoomBy(
                        amount,
                        focus
                )
        )
    }

    override fun zoomBy(amount: Float): CameraUpdate {
        return CameraUpdate.HmsUpdate(com.huawei.hms.maps.CameraUpdateFactory.zoomBy(amount))
    }

    override fun zoomIn(): CameraUpdate {
        return CameraUpdate.HmsUpdate(com.huawei.hms.maps.CameraUpdateFactory.zoomIn())
    }

    override fun zoomOut(): CameraUpdate {
        return CameraUpdate.HmsUpdate(com.huawei.hms.maps.CameraUpdateFactory.zoomOut())
    }

    override fun zoomTo(zoom: Float): CameraUpdate {
        return CameraUpdate.HmsUpdate(com.huawei.hms.maps.CameraUpdateFactory.zoomTo(zoom))
    }

    companion object {
        @Volatile
        private var INSTANCE: HmsCameraUpdateFactory? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        fun getInstance(): HmsCameraUpdateFactory {
            return INSTANCE
                    ?: synchronized(this) {
                        HmsCameraUpdateFactory().also {
                            INSTANCE = it
                        }
                    }
        }
    }
}