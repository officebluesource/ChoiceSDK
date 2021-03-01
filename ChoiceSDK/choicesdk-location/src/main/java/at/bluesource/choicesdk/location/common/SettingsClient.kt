package at.bluesource.choicesdk.location.common

import at.bluesource.choicesdk.core.task.Task

/**
 * Common interface for SettingsClient wrapper
 *
 * When making a request to location services, the device's system settings may be in a state that
 * prevents an app from obtaining the location data that it needs.
 * For example, GPS or Wi-Fi scanning may be switched off.
 *
 * To use the client use [at.bluesource.choicesdk.ChoiceSdk.getLocationSettingsClient] and specify
 * needed requests by using the [LocationSettingsRequest.Builder]
 */
interface SettingsClient {
    fun checkLocationSettings(request: LocationSettingsRequest): Task<LocationSettingsResponse?>
}