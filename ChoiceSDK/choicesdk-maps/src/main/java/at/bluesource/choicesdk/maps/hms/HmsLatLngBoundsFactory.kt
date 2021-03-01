package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds

class HmsLatLngBoundsFactory : LatLngBounds.Factory {

    override fun create(southwest: LatLng, northeast: LatLng): LatLngBounds {
        val bounds = com.huawei.hms.maps.model.LatLngBounds(southwest.toHmsLatLng(), northeast.toHmsLatLng())
        return HmsLatLngBounds(bounds)
    }
}