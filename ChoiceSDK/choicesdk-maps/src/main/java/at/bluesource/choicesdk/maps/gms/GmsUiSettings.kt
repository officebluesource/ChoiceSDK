package at.bluesource.choicesdk.maps.gms

import at.bluesource.choicesdk.maps.common.UiSettings

/**
 * Wrapper class for gms version of UiSettings
 *
 * @property uiSettings gms UiSettings instance
 * @see com.google.android.gms.maps.UiSettings
 */
internal class GmsUiSettings(private val uiSettings: com.google.android.gms.maps.UiSettings) :
    UiSettings {
    override var isCompassEnabled: Boolean
        get() = uiSettings.isCompassEnabled
        set(value) {
            uiSettings.isCompassEnabled = value
        }
    override var isIndoorLevelPickerEnabled: Boolean
        get() = uiSettings.isIndoorLevelPickerEnabled
        set(value) {
            uiSettings.isIndoorLevelPickerEnabled = value
        }
    override var isMapToolbarEnabled: Boolean
        get() = uiSettings.isMapToolbarEnabled
        set(value) {
            uiSettings.isMapToolbarEnabled = value
        }
    override var isMyLocationButtonEnabled: Boolean
        get() = uiSettings.isMyLocationButtonEnabled
        set(value) {
            uiSettings.isMyLocationButtonEnabled = value
        }
    override var isRotateGesturesEnabled: Boolean
        get() = uiSettings.isRotateGesturesEnabled
        set(value) {
            uiSettings.isRotateGesturesEnabled = value
        }
    override var isScrollGesturesEnabled: Boolean
        get() = uiSettings.isScrollGesturesEnabled
        set(value) {
            uiSettings.isScrollGesturesEnabled = value
        }
    override var isScrollGesturesEnabledDuringRotateOrZoom: Boolean
        get() = uiSettings.isScrollGesturesEnabledDuringRotateOrZoom
        set(value) {
            uiSettings.isScrollGesturesEnabledDuringRotateOrZoom = value
        }
    override var isTiltGesturesEnabled: Boolean
        get() = uiSettings.isTiltGesturesEnabled
        set(value) {
            uiSettings.isTiltGesturesEnabled = value
        }
    override var isZoomControlsEnabled: Boolean
        get() = uiSettings.isZoomControlsEnabled
        set(value) {
            uiSettings.isZoomControlsEnabled = value
        }
    override var isZoomGesturesEnabled: Boolean
        get() = uiSettings.isZoomGesturesEnabled
        set(value) {
            uiSettings.isZoomGesturesEnabled = value
        }

    override fun setAllGesturesEnabled(enabled: Boolean) {
        uiSettings.setAllGesturesEnabled(enabled)
    }
}