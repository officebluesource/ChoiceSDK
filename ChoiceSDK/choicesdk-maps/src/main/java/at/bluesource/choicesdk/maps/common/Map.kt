package at.bluesource.choicesdk.maps.common

import android.graphics.Bitmap
import at.bluesource.choicesdk.core.ChoiceSdk
import at.bluesource.choicesdk.core.exception.ApiException
import at.bluesource.choicesdk.maps.common.listener.*
import at.bluesource.choicesdk.maps.common.options.GroundOverlayOptions
import at.bluesource.choicesdk.maps.common.options.MarkerOptions
import at.bluesource.choicesdk.maps.common.options.TileOverlay
import at.bluesource.choicesdk.maps.common.options.TileOverlayOptions
import at.bluesource.choicesdk.maps.common.shape.*
import at.bluesource.choicesdk.maps.gms.GmsMap
import at.bluesource.choicesdk.maps.hms.HmsMap

/**
 * Common interface interface for hms, gms map
 *
 * You should not instantiate a [Map] object directly, rather, you must obtain one from the [ChoiceSdk.getMapFragment] method.
 *
 * You can adjust the viewpoint of a map by changing the position of the camera (as opposed to moving the map).
 * You can use the map's camera to set parameters such as location, zoom level, tilt angle, and bearing with [CameraUpdate].
 *
 * @see com.google.android.gms.maps.GoogleMap
 * @see com.huawei.hms.maps.HuaweiMap
 */
interface Map {

    var mapType: Int
    var maxZoomLevel: Float
    var minZoomLevel: Float
    var isTrafficEnabled: Boolean
    var isIndoorEnabled: Boolean
    var isMyLocationEnabled: Boolean
    var isBuildingsEnabled: Boolean
    val cameraPosition: CameraPosition

    /**
     * Listener
     */

    fun setOnCameraIdleListener(listener: OnCameraIdleListener)
    fun setOnCameraMoveCanceledListener(listener: OnCameraMoveCanceledListener)
    fun setOnCameraMoveListener(listener: OnCameraMoveListener)
    fun setOnCameraMoveStartedListener(listener: OnCameraMoveStartedListener)
    fun setOnCircleClickListener(listener: OnCircleClickListener)
    fun setOnGroundOverlayClickListener(listener: OnGroundOverlayClickListener)
    fun setOnInfoWindowClickListener(listener: OnInfoWindowClickListener)
    fun setOnInfoWindowCloseListener(listener: OnInfoWindowCloseListener)
    fun setOnInfoWindowLongClickListener(listener: OnInfoWindowLongClickListener)
    fun setOnMapClickListener(listener: OnMapClickListener)
    fun setOnMapLoadedCallback(callback: OnMapLoadedCallback)
    fun setOnMapLongClickListener(listener: OnMapLongClickListener)
    fun setOnMarkerClickListener(listener: OnMarkerClickListener)
    fun setOnMarkerDragListener(listener: OnMarkerDragListener)
    fun setOnMyLocationButtonClickListener(listener: OnMyLocationButtonClickListener)
    fun setOnMyLocationClickListener(listener: OnMyLocationClickListener)
    fun setOnPoiClickListener(listener: OnPoiClickListener)
    fun setOnPolygonClickListener(listener: OnPolygonClickListener)
    fun setOnPolyLineClickListener(listener: OnPolylineClickListener)

    /**
     * Other
     */
    @Throws(ApiException::class)
    fun addCircle(options: CircleOptions): Circle
    fun addGroundOverlay(options: GroundOverlayOptions)
    fun addMarker(options: MarkerOptions): Marker
    fun addPolygon(options: PolygonOptions): Polygon
    fun addPolyline(options: PolylineOptions): Polyline
    fun addTileOverlay(options: TileOverlayOptions): TileOverlay
    fun animateCamera(update: CameraUpdate)
    fun animateCamera(update: CameraUpdate, callback: CancelableCallback?)
    fun animateCamera(
        update: CameraUpdate,
        durationMilliseconds: Int,
        callback: CancelableCallback?
    )

    fun clear()
    fun getProjection(): Projection
    fun getUiSettings(): UiSettings
    fun setMaxZoomPreference(maxZoomPreference: Float)
    fun setMinZoomPreference(minZoomPreference: Float)
    fun moveCamera(update: CameraUpdate)
    fun resetMinMaxZoomPreference()
    fun setContentDescription(description: String)
    fun setInfoWindowAdapter(adapter: InfoWindowAdapter)
    fun setLatLngBoundsForCameraTarget(bounds: LatLngBounds)
    fun setLocationSource(source: LocationSource?)
    fun setPadding(left: Int, top: Int, right: Int, bottom: Int)
    fun snapshot(listener: OnSnapshotReadyCallback)
    fun snapshot(listener: OnSnapshotReadyCallback, bitmap: Bitmap)
    fun stopAnimation()

    fun getGoogleMap(): com.google.android.gms.maps.GoogleMap?
    fun getHuaweiMap(): com.huawei.hms.maps.HuaweiMap?

    companion object {

        // Values
        // GMS: https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap#MAP_TYPE_SATELLITE
        // HMS: https://developer.huawei.com/consumer/en/doc/HMSCore-References-V5/huaweimap-0000001050151757-V5#EN-US_TOPIC_0000001050151757__section182805611495
        // but in reality only two values work for HMS: NONE, NORMAL
        const val MAP_TYPE_NONE = 0
        const val MAP_TYPE_NORMAL = 1
        const val MAP_TYPE_SATELLITE = 2
        const val MAP_TYPE_TERRAIN = 3
        const val MAP_TYPE_HYBRID = 4

        internal fun com.google.android.gms.maps.GoogleMap.toChoiceMap(): Map {
            return GmsMap(this@toChoiceMap)
        }

        internal fun com.huawei.hms.maps.HuaweiMap.toChoiceMap(): Map {
            return HmsMap(this@toChoiceMap)
        }
    }
}