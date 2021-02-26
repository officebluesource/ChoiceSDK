package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.SphericalMercatorProjection

class HmsSphericalMercatorProjectionFactory : SphericalMercatorProjection.Factory {
    override fun create(worldWidth: Double): SphericalMercatorProjection {
        return HmsSphericalMercatorProjection(worldWidth)
    }
}