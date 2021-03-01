package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.MapOptions

class GmsMapOptionsFactory : MapOptions.Factory {
    override fun create(): MapOptions {
        return GmsMapOptions()
    }
}