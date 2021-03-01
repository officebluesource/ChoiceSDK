package at.bluesource.choicesdk.location.common

import android.content.Intent
import android.location.Location

/**
 * Common interface for LocationResult
 *
 * A data class representing a geographic location result from the fused location provider.
 *
 * @see com.google.android.gms.location.LocationResult
 * @see com.huawei.hms.location.LocationResult
 */
interface LocationResult {

    val lastLocation: Location?
    val locations: List<Location>
    fun hasResult(intent: Intent): Boolean
    override fun toString(): String

    companion object {

        internal fun com.google.android.gms.location.LocationResult.toChoiceLocationResult(): LocationResult {
            return object : LocationResult {
                override val lastLocation: Location?
                    get() = this@toChoiceLocationResult.lastLocation

                override val locations: List<Location>
                    get() = this@toChoiceLocationResult.locations

                override fun hasResult(intent: Intent): Boolean {
                    return com.google.android.gms.location.LocationResult.hasResult(intent)
                }

                override fun toString(): String {
                    return this@toChoiceLocationResult.toString()
                }
            }
        }

        internal fun com.huawei.hms.location.LocationResult.toChoiceLocationResult(): LocationResult {
            return object : LocationResult {
                override val lastLocation: Location?
                    get() = this@toChoiceLocationResult.lastLocation

                override val locations: List<Location>
                    get() = this@toChoiceLocationResult.locations

                override fun hasResult(intent: Intent): Boolean {
                    return com.huawei.hms.location.LocationResult.hasResult(intent)
                }

                override fun toString(): String {
                    return this@toChoiceLocationResult.toString()
                }
            }
        }
    }
}