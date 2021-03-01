package at.bluesource.choicesdk.maps.hms

import at.bluesource.choicesdk.maps.common.UiSettings

/**
 * Wrapper class for hms version of UiSettings
 *
 * @property uiSettings hms UiSettings instance
 * @see com.huawei.hms.maps.UiSettings
 */
internal class HmsUiSettings(private val uiSettings: com.huawei.hms.maps.UiSettings) : UiSettings {
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