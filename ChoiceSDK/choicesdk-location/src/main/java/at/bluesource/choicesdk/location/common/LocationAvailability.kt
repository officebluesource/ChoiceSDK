package at.bluesource.choicesdk.location.common

import android.content.Intent
import at.bluesource.choicesdk.core.MobileServicesDetector


/**
 * Common interface for LocationAvailability
 *
 * Gives a status on the availability of location data.
 *
 * @see com.google.android.gms.location.LocationAvailability
 * @see com.huawei.hms.location.LocationAvailability
 */
interface LocationAvailability {

    val isLocationAvailable: Boolean
    val cellStatus: Int
    val wifiStatus: Int
    val elapsedRealtimeNs: Long
    val locationStatus: Int
    override fun toString(): String

    @Suppress("ExplicitThis")
    companion object {
        fun extractLocationAvailability(intent: Intent?): LocationAvailability? {
            return when {
                MobileServicesDetector.isGmsAvailable() -> {
                    return com.google.android.gms.location.LocationAvailability.extractLocationAvailability(
                        intent
                    )?.toChoiceLocationAvailability()
                }
                MobileServicesDetector.isHmsAvailable() -> {
                    return com.huawei.hms.location.LocationAvailability.extractLocationAvailability(
                        intent
                    ).toChoiceLocationAvailability()
                }
                else -> null
            }
        }

        fun hasLocationAvailability(intent: Intent?): Boolean {
            return when {
                MobileServicesDetector.isGmsAvailable() -> {
                    return com.google.android.gms.location.LocationAvailability.hasLocationAvailability(
                        intent
                    )
                }
                MobileServicesDetector.isHmsAvailable() -> {
                    return com.huawei.hms.location.LocationAvailability.hasLocationAvailability(
                        intent
                    )
                }
                else -> false
            }
        }

        internal fun com.google.android.gms.location.LocationAvailability.toChoiceLocationAvailability(): LocationAvailability {
            return object : LocationAvailability {
                override val isLocationAvailable: Boolean
                    get() = this@toChoiceLocationAvailability.isLocationAvailable
                override val cellStatus: Int
                    get() = -1
                override val wifiStatus: Int
                    get() = -1
                override val elapsedRealtimeNs: Long
                    get() = -1
                override val locationStatus: Int
                    get() = -1

                override fun toString(): String {
                    return this@toChoiceLocationAvailability.toString()
                }
            }
        }

        internal fun com.huawei.hms.location.LocationAvailability.toChoiceLocationAvailability(): LocationAvailability {
            return object : LocationAvailability {
                override val isLocationAvailable: Boolean
                    get() = this@toChoiceLocationAvailability.isLocationAvailable
                override val cellStatus: Int
                    get() = this@toChoiceLocationAvailability.cellStatus
                override val wifiStatus: Int
                    get() = this@toChoiceLocationAvailability.wifiStatus
                override val elapsedRealtimeNs: Long
                    get() = this@toChoiceLocationAvailability.elapsedRealtimeNs
                override val locationStatus: Int
                    get() = this@toChoiceLocationAvailability.locationStatus

                override fun toString(): String {
                    return this@toChoiceLocationAvailability.toString()
                }
            }
        }
    }
}