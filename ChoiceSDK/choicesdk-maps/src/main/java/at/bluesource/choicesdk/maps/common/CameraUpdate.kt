package at.bluesource.choicesdk.maps.common

import at.bluesource.choicesdk.core.ChoiceSdk

/**
 * Wrapper class for hms and gms CameraUpdate.
 * Has inner classes which hold their specific version.
 *
 * Defines a camera move. An object of this type can be used to modify a map's camera by calling [Map.animateCamera] or [Map.moveCamera].
 * To obtain a CameraUpdate use [ChoiceSdk.getCameraUpdateFactory] or [CameraUpdateFactory.instance]
 *
 * @see com.google.android.gms.maps.CameraUpdate
 * @see com.huawei.hms.maps.CameraUpdate
 */
sealed class CameraUpdate {

    internal data class GmsUpdate(val value: com.google.android.gms.maps.CameraUpdate) :
            CameraUpdate()

    internal data class HmsUpdate(val value: com.huawei.hms.maps.CameraUpdate) :
            CameraUpdate()

    companion object {

        @JvmStatic
        fun com.google.android.gms.maps.CameraUpdate.toChoice(): CameraUpdate {
            return GmsUpdate(this)
        }

        @JvmStatic
        internal fun com.huawei.hms.maps.CameraUpdate.toChoice(): CameraUpdate {
            return HmsUpdate(this)
        }
    }
}