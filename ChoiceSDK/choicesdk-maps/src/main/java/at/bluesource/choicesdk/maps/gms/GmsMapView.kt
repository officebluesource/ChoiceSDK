package at.bluesource.choicesdk.maps.gms

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import at.bluesource.choicesdk.maps.common.Map.Companion.toChoiceMap
import at.bluesource.choicesdk.maps.common.MapOptions
import at.bluesource.choicesdk.maps.common.MapOptions.Companion.toGmsMapOptions
import at.bluesource.choicesdk.maps.common.MapView
import at.bluesource.choicesdk.maps.common.OnMapReadyCallback

class GmsMapView : MapView {

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        mapView = com.google.android.gms.maps.MapView(context, attrs, defStyleAttr)
    }

    constructor(context: Context, options: MapOptions) : super(context) {
        mapView = com.google.android.gms.maps.MapView(context, options.toGmsMapOptions())
    }

    private val mapView: com.google.android.gms.maps.MapView

    override fun onCreate(mapViewSavedInstanceState: Bundle?) {
        mapView.onCreate(mapViewSavedInstanceState)
    }

    override fun onStart() {
        mapView.onStart()
    }

    override fun onResume() {
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
    }

    override fun onStop() {
        mapView.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(mapViewSaveState: Bundle) {
        mapView.onSaveInstanceState(mapViewSaveState)
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
    }

    override fun getMapAsync(callback: OnMapReadyCallback) {
        mapView.getMapAsync {
            callback.onMapReady(it.toChoiceMap())
        }
    }
}