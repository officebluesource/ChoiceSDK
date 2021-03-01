package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds
import at.bluesource.choicesdk.maps.gms.GmsLatLngBounds

class HmsLatLngBounds(private val bounds: com.huawei.hms.maps.model.LatLngBounds) : LatLngBounds {

    constructor(southwest: LatLng, northeast: LatLng) :
            this(
                com.huawei.hms.maps.model.LatLngBounds(
                    southwest.toHmsLatLng(),
                    northeast.toHmsLatLng()
                )
            )

    override val southwest: LatLng
        get() = bounds.southwest.toChoiceLatLng()

    override val northeast: LatLng
        get() = bounds.northeast.toChoiceLatLng()

    override fun contains(point: LatLng): Boolean {
        return bounds.contains(point.toHmsLatLng())
    }

    override fun including(point: LatLng): LatLngBounds {
        val updated = bounds.including(point.toHmsLatLng())
        return HmsLatLngBounds(updated)
    }

    override fun getCenter(): LatLng {
        return bounds.center.toChoiceLatLng()
    }


}