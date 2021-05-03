package at.bluesource.choicesdk.maps.common

internal fun interface LatLngBoundsBuilder {
    fun include(point: LatLng): LatLngBoundsBuilder
}