package at.bluesource.choicesdk.maps.common

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.FrameLayout
import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.gms.GmsMapView
import at.bluesource.choicesdk.maps.hms.HmsMapView

abstract class MapView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    abstract fun onCreate(mapViewSavedInstanceState: Bundle?)
    abstract fun onStart()
    abstract fun onResume()
    abstract fun onPause()
    abstract fun onStop()
    abstract fun onDestroy()
    abstract fun onSaveInstanceState(mapViewSaveState: Bundle)
    abstract fun onLowMemory()
    abstract fun getMapAsync(callback: OnMapReadyCallback)

    companion object Factory {
        fun create(context: Context): MapView {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsMapView(context)
                MobileService.HMS -> HmsMapView(context)
            }
        }

        fun create(context: Context, options: MapOptions): MapView {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsMapView(context, options)
                MobileService.HMS -> HmsMapView(context, options)
            }
        }
    }
}