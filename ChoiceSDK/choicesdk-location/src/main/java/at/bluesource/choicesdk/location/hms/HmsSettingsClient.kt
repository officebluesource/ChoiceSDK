package at.bluesource.choicesdk.location.hms

import at.bluesource.choicesdk.core.task.HmsTask
import at.bluesource.choicesdk.core.task.Task
import at.bluesource.choicesdk.location.common.LocationSettingsRequest
import at.bluesource.choicesdk.location.common.LocationSettingsRequest.Companion.toHmsLocationSettingsRequest
import at.bluesource.choicesdk.location.common.LocationSettingsResponse
import at.bluesource.choicesdk.location.common.SettingsClient
import at.bluesource.choicesdk.location.continuation.ContinuationWithConversion

/**
 * Wrapper class for hms version of SettingsClient
 *
 * @property settingsClient hms SettingsClient instance
 * @see com.huawei.hms.location.SettingsClient
 */
internal class HmsSettingsClient(private val settingsClient: com.huawei.hms.location.SettingsClient) :
    SettingsClient {

    override fun checkLocationSettings(request: LocationSettingsRequest): Task<LocationSettingsResponse?> {
        return HmsTask(settingsClient.checkLocationSettings(request.toHmsLocationSettingsRequest())).continueWith(
            object :
                ContinuationWithConversion<com.huawei.hms.location.LocationSettingsResponse?, LocationSettingsResponse?>() {
                override fun convertResult(result: com.huawei.hms.location.LocationSettingsResponse?): LocationSettingsResponse? {
                    return HmsLocationSettingsResponse(
                        result
                    )
                }
            }
        )
    }
}