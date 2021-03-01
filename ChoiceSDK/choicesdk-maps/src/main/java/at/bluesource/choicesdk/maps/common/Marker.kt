package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.maps.gms.GmsMarker
import at.bluesource.choicesdk.maps.hms.HmsMarker

/**
 * Common interface for hms and gms marker
 *
 * Marker should not be created, only the specific [Map] can create new instances
 * thus it is not advised to create object from this interface.
 *
 * @see at.bluesource.choicesdk.maps.gms.GmsMarker
 * @see at.bluesource.choicesdk.maps.hms.HmsMarker
 */
interface Marker {
    val id: String
    var position: LatLng
    var zIndex: Float
    var title: String?
    var snippet: String?
    var draggable: Boolean
    val infoWindowShown: Boolean
    var visible: Boolean
    var flat: Boolean
    var rotation: Float
    var alpha: Float
    var tag: Any?

    fun remove()
    fun setIcon(bitmapDescriptor: BitmapDescriptor)
    fun setAnchor(anchorU: Float, anchorV: Float)
    fun setInfoWindowAnchor(anchorU: Float, anchorV: Float)
    fun showInfoWindow()
    fun hideInfoWindow()
    fun isInfoWindowShown(): Boolean
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int

    companion object {

        @JvmStatic
        fun com.google.android.gms.maps.model.Marker.toChoiceMarker(): Marker {
            return GmsMarker(this)
        }

        internal fun com.huawei.hms.maps.model.Marker.toChoiceMarker(): Marker {
            return HmsMarker(this)
        }
    }
}