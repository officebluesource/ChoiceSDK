package at.bluesource.choicesdk.maps.common

internal interface LatLngBoundsBuilder {

    fun include(point: LatLng): LatLngBoundsBuilder

    companion object {

    }
}