package at.bluesource.choicesdk.location.common

import android.content.Intent
import at.bluesource.choicesdk.core.MobileServicesDetector

/**
 * Common interface for LocationSettingsStates
 *
 * Stores the current states of all location-related settings.
 *
 * @see com.google.android.gms.location.LocationSettingsStates
 * @see com.huawei.hms.location.LocationSettingsStates
 */
interface LocationSettingsStates {
    val isGpsUsable: Boolean
    val isGpsPresent: Boolean
    val isNetworkLocationUsable: Boolean
    val isNetworkLocationPresent: Boolean
    val isLocationUsable: Boolean
    val isLocationPresent: Boolean
    val isBleUsable: Boolean
    val isBlePresent: Boolean

    @Suppress("unused")
    companion object {

        fun fromIntent(intent: Intent): LocationSettingsStates? {

            return when {
                MobileServicesDetector.isGmsAvailable() -> {
                    com.google.android.gms.location.LocationSettingsStates.fromIntent(intent)?.toChoiceLocationSettingsStates()
                }
                MobileServicesDetector.isHmsAvailable() -> {
                    com.huawei.hms.location.LocationSettingsStates.fromIntent(intent)?.toChoiceLocationSettingsStates()
                }
                else -> null
            }
        }

        internal fun com.google.android.gms.location.LocationSettingsStates.toChoiceLocationSettingsStates(): LocationSettingsStates? {
            return object :
                LocationSettingsStates {
                override val isGpsUsable: Boolean
                    get() = this@toChoiceLocationSettingsStates.isGpsUsable
                override val isGpsPresent: Boolean
                    get() = this@toChoiceLocationSettingsStates.isGpsPresent
                override val isNetworkLocationUsable: Boolean
                    get() = this@toChoiceLocationSettingsStates.isNetworkLocationUsable
                override val isNetworkLocationPresent: Boolean
                    get() = this@toChoiceLocationSettingsStates.isNetworkLocationPresent
                override val isLocationUsable: Boolean
                    get() = this@toChoiceLocationSettingsStates.isLocationUsable
                override val isLocationPresent: Boolean
                    get() = this@toChoiceLocationSettingsStates.isLocationPresent
                override val isBleUsable: Boolean
                    get() = this@toChoiceLocationSettingsStates.isBleUsable
                override val isBlePresent: Boolean
                    get() = this@toChoiceLocationSettingsStates.isBlePresent
            }
        }

        internal fun com.huawei.hms.location.LocationSettingsStates.toChoiceLocationSettingsStates(): LocationSettingsStates? {
            return object :
                LocationSettingsStates {
                override val isGpsUsable: Boolean
                    get() = this@toChoiceLocationSettingsStates.isGpsUsable
                override val isGpsPresent: Boolean
                    get() = this@toChoiceLocationSettingsStates.isGpsPresent
                override val isNetworkLocationUsable: Boolean
                    get() = this@toChoiceLocationSettingsStates.isNetworkLocationUsable
                override val isNetworkLocationPresent: Boolean
                    get() = this@toChoiceLocationSettingsStates.isNetworkLocationPresent
                override val isLocationUsable: Boolean
                    get() = this@toChoiceLocationSettingsStates.isLocationUsable
                override val isLocationPresent: Boolean
                    get() = this@toChoiceLocationSettingsStates.isLocationPresent
                override val isBleUsable: Boolean
                    get() = this@toChoiceLocationSettingsStates.isBleUsable
                override val isBlePresent: Boolean
                    get() = this@toChoiceLocationSettingsStates.isBlePresent
            }
        }
    }
}