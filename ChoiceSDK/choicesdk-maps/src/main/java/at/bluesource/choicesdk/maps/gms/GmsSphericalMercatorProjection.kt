package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toChoiceLatLng
import at.bluesource.choicesdk.maps.common.LatLng.Companion.toGmsLatLng
import at.bluesource.choicesdk.maps.common.Point
import at.bluesource.choicesdk.maps.common.Point.Companion.toChoicePoint
import at.bluesource.choicesdk.maps.common.Point.Companion.toGmsPoint
import at.bluesource.choicesdk.maps.common.SphericalMercatorProjection

internal class GmsSphericalMercatorProjection(worldWidth: Double) : SphericalMercatorProjection() {

    private val projection = com.google.maps.android.projection.SphericalMercatorProjection(worldWidth)

    override fun toPoint(latLng: LatLng): Point {
        return projection.toPoint(latLng.toGmsLatLng()).toChoicePoint()
    }

    override fun toLatLng(point: Point): LatLng {
        return projection.toLatLng(point.toGmsPoint()).toChoiceLatLng()
    }

}