package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.LatLng
import at.bluesource.choicesdk.maps.common.Point
import at.bluesource.choicesdk.maps.common.SphericalMercatorProjection
import kotlin.math.atan
import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.sin

internal class HmsSphericalMercatorProjection(private val worldWidth: Double) : SphericalMercatorProjection() {

    override fun toPoint(latLng: LatLng): Point {
        val x = latLng.longitude / 360.0 + 0.5
        val sinY = sin(Math.toRadians(latLng.latitude))
        val y = 0.5 * ln((1.0 + sinY) / (1.0 - sinY)) / -6.283185307179586 + 0.5
        return Point(x * worldWidth, y * worldWidth)
    }

    override fun toLatLng(point: Point): LatLng {
        val x: Double = point.x / worldWidth - 0.5
        val lng = x * 360.0
        val y: Double = 0.5 - point.y / worldWidth
        val lat = 90.0 - Math.toDegrees(atan(exp(-y * 2.0 * 3.141592653589793)) * 2.0)
        return LatLng(lat, lng)
    }

}