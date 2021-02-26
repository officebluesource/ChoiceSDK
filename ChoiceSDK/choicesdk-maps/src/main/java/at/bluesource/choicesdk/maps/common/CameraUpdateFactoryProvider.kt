package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.gms.GmsCameraUpdateFactory
import at.bluesource.choicesdk.maps.hms.HmsCameraUpdateFactory

object CameraUpdateFactoryProvider {

    @JvmStatic
    fun get(): CameraUpdateFactory {

        return when {
            MobileServicesDetector.isGmsAvailable() -> {
                GmsCameraUpdateFactory.getInstance()
            }
            MobileServicesDetector.isHmsAvailable() -> {
                HmsCameraUpdateFactory.getInstance()
            }
            else -> {
                throw UnsupportedOperationException("Missing underlying GMS/HMS API.")
            }
        }
    }

}