package at.bluesource.choicesdk.location.common

/**
 * Common interface for LocationSettingsResponse wrapper
 *
 * Successful response of checking settings via [SettingsClient.checkLocationSettings].
 *
 * If a Task with this response type fails, it will receive a [at.bluesource.choicesdk.core.exception.ResolvableApiException]
 * which may be able to resolve the failure. See [SettingsClient] for more details.
 */
interface LocationSettingsResponse {
    fun getLocationSettingsStates(): LocationSettingsStates?
}