package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds

class GmsLatLngBounds(private val bounds: com.google.android.gms.maps.model.LatLngBounds) : LatLngBounds {

    constructor(southwest: LatLng, northeast: LatLng) :
            this(
                com.google.android.gms.maps.model.LatLngBounds(
                    southwest.toGmsLatLng(),
                    northeast.toGmsLatLng()
                )
            )

    override val southwest: LatLng
        get() = bounds.southwest.toChoiceLatLng()

    override val northeast: LatLng
        get() = bounds.northeast.toChoiceLatLng()

    override fun contains(point: LatLng): Boolean {
        return bounds.contains(point.toGmsLatLng())
    }

    override fun including(point: LatLng): LatLngBounds {
        val updated = bounds.including(point.toGmsLatLng())
        return GmsLatLngBounds(updated)
    }

    override fun getCenter(): LatLng {
        return bounds.center.toChoiceLatLng()
    }

}