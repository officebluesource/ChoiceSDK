package at.bluesource.choicesdk.maps.common

import android.graphics.Point
import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.gms.GmsCameraUpdateFactory
import at.bluesource.choicesdk.maps.hms.HmsCameraUpdateFactory

/**
 * Common interface for hms and gms CameraUpdateFactory
 *
 * A factory containing methods for creating [CameraUpdate] objects that change a map's camera.
 * To modify the map's camera, call [Map.animateCamera] or [Map.moveCamera], using a CameraUpdate object created with this class.
 *
 * @see com.google.android.gms.maps.CameraUpdateFactory
 * @see com.huawei.hms.maps.CameraUpdateFactory
 */
interface CameraUpdateFactory {
    fun newCameraPosition(cameraPosition: CameraPosition): CameraUpdate
    fun newLatLng(latLng: LatLng): CameraUpdate

    fun newLatLngBounds(bounds: LatLngBounds, padding: Int): CameraUpdate

    fun newLatLngBounds(
        latLngNorthEast: LatLng,
        latLngSouthWest: LatLng,
        width: Int,
        height: Int,
        padding: Int
    ): CameraUpdate

    fun newLatLngBounds(
        latLngNorthEast: LatLng,
        latLngSouthWest: LatLng,
        padding: Int
    ): CameraUpdate

    fun newLatLngZoom(latLng: LatLng, zoom: Float): CameraUpdate
    fun scrollBy(xPixel: Float, yPixel: Float): CameraUpdate
    fun zoomBy(amount: Float, focus: Point): CameraUpdate
    fun zoomBy(amount: Float): CameraUpdate
    fun zoomIn(): CameraUpdate
    fun zoomOut(): CameraUpdate
    fun zoomTo(zoom: Float): CameraUpdate

    companion object Provider {

        @JvmStatic
        fun get(): CameraUpdateFactory {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsCameraUpdateFactory.getInstance()
                MobileService.HMS -> HmsCameraUpdateFactory.getInstance()
            }
        }
    }

}
