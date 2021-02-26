package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toHmsLatLng
import at.bluesource.choicesdk.maps.common.LatLngBounds
import at.bluesource.choicesdk.maps.common.LatLngBounds.Companion.toChoiceLatLngBounds

class HmsLatLngBoundsBuilder : LatLngBounds.Builder {

    private val builder = com.huawei.hms.maps.model.LatLngBounds.Builder()

    override fun include(point: LatLng): LatLngBounds.Builder {
        builder.include(point.toHmsLatLng())
        return this
    }

    override fun build(): LatLngBounds {
        return builder.build().toChoiceLatLngBounds()
    }
}