package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.MapOptions

class HmsMapOptionsFactory : MapOptions.Factory {
    override fun create(): MapOptions {
        return HmsMapOptions()
    }
}