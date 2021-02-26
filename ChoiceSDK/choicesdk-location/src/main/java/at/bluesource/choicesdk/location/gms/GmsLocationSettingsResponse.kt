package at.bluesource.choicesdk.location.gms

import at.bluesource.choicesdk.location.common.LocationSettingsResponse
import at.bluesource.choicesdk.location.common.LocationSettingsStates
import at.bluesource.choicesdk.location.common.LocationSettingsStates.Companion.toChoiceLocationSettingsStates

/**
 * Wrapper class for gms version of LocationSettingsResponse
 *
 * @property locationSettingsResponse gms LocationSettingsResponse instance
 * @see com.google.android.gms.location.LocationSettingsResponse
 */
internal class GmsLocationSettingsResponse(private val locationSettingsResponse: com.google.android.gms.location.LocationSettingsResponse?) :
    LocationSettingsResponse {

    override fun getLocationSettingsStates(): LocationSettingsStates? {
        return when (locationSettingsResponse) {
            null -> null
            else -> locationSettingsResponse.locationSettingsStates?.toChoiceLocationSettingsStates()
        }
    }
}