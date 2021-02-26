package at.bluesource.choicesdk.location.gms

import at.bluesource.choicesdk.core.task.GmsTask
import at.bluesource.choicesdk.core.task.Task
import at.bluesource.choicesdk.location.common.LocationSettingsRequest
import at.bluesource.choicesdk.location.common.LocationSettingsRequest.Companion.toGmsLocationSettingsRequest
import at.bluesource.choicesdk.location.common.LocationSettingsResponse
import at.bluesource.choicesdk.location.common.SettingsClient
import at.bluesource.choicesdk.location.continuation.ContinuationWithConversion

/**
 * Wrapper class for gms version of SettingsClient
 *
 * @property settingsClient gms SettingsClient instance
 * @see com.google.android.gms.location.SettingsClient
 */
internal class GmsSettingsClient(private val settingsClient: com.google.android.gms.location.SettingsClient) :
    SettingsClient {
    override fun checkLocationSettings(request: LocationSettingsRequest): Task<LocationSettingsResponse?> {
        return GmsTask(settingsClient.checkLocationSettings(request.toGmsLocationSettingsRequest())).continueWith(
            object :
                ContinuationWithConversion<com.google.android.gms.location.LocationSettingsResponse?, LocationSettingsResponse?>() {
                override fun convertResult(result: com.google.android.gms.location.LocationSettingsResponse?): LocationSettingsResponse {
                    return GmsLocationSettingsResponse(
                        result
                    )
                }
            }
        )
    }
}