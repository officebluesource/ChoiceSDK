package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.maps.gms.GmsUiSettings
import at.bluesource.choicesdk.maps.hms.HmsUiSettings

/**
 * Common interface for hms and gms ui settings
 *
 * Settings for the user interface of a [Map]. To obtain this interface, call [Map.getUiSettings].
 *
 * @see at.bluesource.choicesdk.maps.gms.GmsUiSettings
 * @see at.bluesource.choicesdk.maps.hms.HmsUiSettings
 */
interface UiSettings {

    var isCompassEnabled: Boolean
    var isIndoorLevelPickerEnabled: Boolean
    var isMapToolbarEnabled: Boolean
    var isMyLocationButtonEnabled: Boolean
    var isRotateGesturesEnabled: Boolean
    var isScrollGesturesEnabled: Boolean
    var isScrollGesturesEnabledDuringRotateOrZoom: Boolean
    var isTiltGesturesEnabled: Boolean
    var isZoomControlsEnabled: Boolean
    var isZoomGesturesEnabled: Boolean

    fun setAllGesturesEnabled(enabled: Boolean)

    companion object {
        internal fun com.google.android.gms.maps.UiSettings.toChoiceUiSettings(): UiSettings {
            return GmsUiSettings(this)
        }

        internal fun com.huawei.hms.maps.UiSettings.toChoiceUiSettings(): UiSettings {
            return HmsUiSettings(this)
        }
    }
}