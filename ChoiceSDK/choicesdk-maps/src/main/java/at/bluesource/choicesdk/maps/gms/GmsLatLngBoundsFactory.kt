package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds

class GmsLatLngBoundsFactory : LatLngBounds.Factory {

    override fun create(southwest: LatLng, northeast: LatLng): LatLngBounds {
        return GmsLatLngBounds(southwest, northeast)
    }
}