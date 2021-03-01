package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng

class GmsLatLngFactory : LatLng.Factory {
    override fun create(latitude: Double, longitude: Double): LatLng {
        return com.google.android.gms.maps.model.LatLng(latitude, longitude).toChoiceLatLng()
    }
}