package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng

class HmsLatLngFactory : LatLng.Factory {
    override fun create(latitude: Double, longitude: Double): LatLng {
        return com.huawei.hms.maps.model.LatLng(latitude, longitude).toChoiceLatLng()
    }
}