package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toChoiceLatLngBounds

class GmsLatLngBoundsBuilder : LatLngBounds.Builder {

    private val builder = com.google.android.gms.maps.model.LatLngBounds.Builder()

    override fun include(point: LatLng): LatLngBounds.Builder {
        builder.include(point.toGmsLatLng())
        return this
    }

    override fun build(): LatLngBounds {
        return builder.build().toChoiceLatLngBounds()
    }
}