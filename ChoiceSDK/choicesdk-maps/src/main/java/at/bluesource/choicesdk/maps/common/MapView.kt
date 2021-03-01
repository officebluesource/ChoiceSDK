package at.bluesource.choicesdk.maps.common

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.FrameLayout
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
            return when {
                MobileServicesDetector.isGmsAvailable() -> GmsMapView(context)
                MobileServicesDetector.isHmsAvailable() -> HmsMapView(context)
                else -> throw IllegalStateException("Neither GMS nor HMS is available.")
            }
        }

        fun create(context: Context, options: MapOptions): MapView {
            return when {
                MobileServicesDetector.isGmsAvailable() -> GmsMapView(context, options)
                MobileServicesDetector.isHmsAvailable() -> HmsMapView(context, options)
                else -> throw IllegalStateException("Neither GMS nor HMS is available.")
            }
        }
    }
}