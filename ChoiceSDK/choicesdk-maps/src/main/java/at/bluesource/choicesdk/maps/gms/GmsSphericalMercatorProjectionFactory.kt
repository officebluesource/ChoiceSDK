package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.SphericalMercatorProjection

class GmsSphericalMercatorProjectionFactory : SphericalMercatorProjection.Factory {
    override fun create(worldWidth: Double): SphericalMercatorProjection {
        return GmsSphericalMercatorProjection(worldWidth)
    }
}