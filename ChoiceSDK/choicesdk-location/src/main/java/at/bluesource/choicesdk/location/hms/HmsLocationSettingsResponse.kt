package at.bluesource.choicesdk.location.hms

import at.bluesource.choicesdk.location.common.LocationSettingsResponse
import at.bluesource.choicesdk.location.common.LocationSettingsStates
import at.bluesource.choicesdk.location.common.LocationSettingsStates.Companion.toChoiceLocationSettingsStates

/**
 * Wrapper class for hms version of LocationSettingsResponse
 *
 * @property locationSettingsResponse hms LocationSettingsResponse instance
 * @see com.huawei.hms.location.LocationSettingsResponse
 */
internal class HmsLocationSettingsResponse(private val locationSettingsResponse: com.huawei.hms.location.LocationSettingsResponse?) :
    LocationSettingsResponse {

    override fun getLocationSettingsStates(): LocationSettingsStates? {
        return when (locationSettingsResponse) {
            null -> null
            else -> locationSettingsResponse.locationSettingsStates?.toChoiceLocationSettingsStates()
        }
    }
}