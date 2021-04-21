package com.bluesource.choicesdk_app.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.core.task.listener.OnSuccessListener
import at.bluesource.choicesdk.location.common.FusedLocationProviderClient
import at.bluesource.choicesdk.location.factory.FusedLocationProviderFactory
import at.bluesource.choicesdk.maps.common.*
import at.bluesource.choicesdk.maps.common.listener.OnCircleClickListener
import at.bluesource.choicesdk.maps.common.listener.OnGroundOverlayClickListener
import at.bluesource.choicesdk.maps.common.listener.OnMarkerClickListener
import at.bluesource.choicesdk.maps.common.options.GroundOverlayOptions
import at.bluesource.choicesdk.maps.common.options.MarkerOptions
import at.bluesource.choicesdk.maps.common.shape.*
import com.bluesource.choicesdk_app.R
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver


class MapActivity : AppCompatActivity() {
    private val TAG = "Test"
    private lateinit var mapDisposable: Disposable
    private lateinit var mapFragment: MapFragment

    private val PERMISSIONS = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.INTERNET
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        mapFragment = MapFragment.newInstance()
        fragmentTransaction.add(R.id.mapContainer, mapFragment)
        fragmentTransaction.commit()

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 3)
        } else {
            testMap()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 3) {
            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show()
                testMap()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }

    private fun testMap() {
        mapDisposable =
            mapFragment.getMapObservable().subscribeWith(object : DisposableObserver<at.bluesource.choicesdk.maps.common.Map>() {
                override fun onComplete() {
                    Log.e(TAG, "Completed.")
                }

                override fun onNext(map: at.bluesource.choicesdk.maps.common.Map) {
                    val uiSettings = map.getUiSettings()
                    uiSettings.isCompassEnabled = true
                    uiSettings.isMyLocationButtonEnabled = true
                    uiSettings.isRotateGesturesEnabled = true
                    uiSettings.isMapToolbarEnabled = true

                    map.isMyLocationEnabled = true
                    map.mapType = at.bluesource.choicesdk.maps.common.Map.MAP_TYPE_NORMAL

                    val fused: FusedLocationProviderClient =
                        FusedLocationProviderFactory.getFusedLocationProviderClient(this@MapActivity)
                    if (ActivityCompat.checkSelfPermission(
                            this@MapActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this@MapActivity,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {

                        fused.getLastLocation()
                            .addOnSuccessListener(object : OnSuccessListener<Location?> {
                                override fun onSuccess(result: Location?) {
                                    if (result != null) {
                                        mapLocationTest(result, map)
                                    }
                                }
                            })
                    }

                    map.addMarker(
                        MarkerOptions.create()
                            .defaultIcon()
                            .position(
                                LatLng(
                                    47.871026,
                                    13.548118
                                )
                            )
                    )
                }

                override fun onError(e: Throwable) {
                    Log.e(TAG, "${e.message}")
                }
            })
    }

    private fun mapLocationTest(
        result: Location,
        map: at.bluesource.choicesdk.maps.common.Map
    ) {
        val location = LatLng(result.latitude, result.longitude)
        val location2 = LatLng(result.latitude - 0.0005, result.longitude - 0.0005)
        val location3 = LatLng(result.latitude + 0.0005, result.longitude + 0.0005)
        val location4 = LatLng(result.latitude - 0.001, result.longitude + 0.001)
        val location5 = LatLng(result.latitude + 0.002, result.longitude + 0.003)
        val location6 = LatLng(result.latitude + 0.003, result.longitude + 0.003)
        val location7 = LatLng(result.latitude + 0.004, result.longitude + 0.002)
        val location8 = LatLng(result.latitude + 0.005, result.longitude + 0.001)
        val location9 = LatLng(result.latitude + 0.006, result.longitude + 0.002)

        val locationHole1: LatLng = LatLng.midPoint(location4, location5)

        map.animateCamera(
            CameraUpdateFactory.get()
                .newLatLngZoom(location, 13f),
            4000,
            null
        )

        map.addCircle(
            Circle.CircleOptions()
                .center(location)
                .radius(100.0)
                .fillColor(Color.YELLOW)
                .strokePattern(
                    listOf(
                        PatternItem.Dot(),
                        PatternItem.Dash(10f),
                        PatternItem.Gap(20f)
                    )
                )
                .clickable(true)
        )

        map.addPolyline(
            PolylineOptions()
                .add(location2, location3)
                .strokeWidth(15f)
                .startCap(Cap.RoundCap())
                .endCap(
                    Cap.CustomCap(
                        BitmapDescriptorFactory.Provider.instance()
                            .fromResource(R.drawable.common_full_open_on_phone), 50f
                    )
                )
                .zIndex(2f)
        )

        map.addPolygon(
            PolygonOptions()
                .add(location4)
                .add(location5)
                .add(location6)
                .fillColor(Color.RED)
                .strokeColor(Color.GREEN)
                .strokeWidth(3f)
                .addHole(listOf(locationHole1, location6))
        )

        map.addMarker(
            MarkerOptions.create()
                .icon(
                    BitmapDescriptorFactory.Provider.instance()
                        .fromResource(R.drawable.common_full_open_on_phone)
                )
                .position(location4)
                .title("Location4")
                .draggable(true)
                .anchor(0.5f, 1f)
                .clusterable(true)
        )
        map.addMarker(
            MarkerOptions.create()
                .defaultIcon()
                .position(location5)
                .title("Location5")
                .draggable(true)
                .anchor(0.5f, 1f)
                .clusterable(true)
        )
        map.addMarker(
            MarkerOptions.create()
                .defaultIcon()
                .position(location6)
                .title("Location6")
                .draggable(true)
                .anchor(0.5f, 1f)
                .clusterable(true)
        )
        map.addMarker(
            MarkerOptions.create()
                .defaultIcon()
                .position(location7)
                .title("Location7")
                .draggable(true)
                .anchor(0.5f, 1f)
                .clusterable(true)
        )
        map.addMarker(
            MarkerOptions.create()
                .defaultIcon()
                .position(location8)
                .title("Location8")
                .draggable(true)
                .anchor(0.5f, 1f)
                .clusterable(true)
        )
        map.addMarker(
            MarkerOptions.create()
                .defaultIcon()
                .position(location9)
                .title("Location9")
                .draggable(true)
                .anchor(0.5f, 1f)
                .clusterable(true)
        )
        map.addMarker(
            MarkerOptions.create()
                .defaultIcon()
                .position(LatLng.midPoint(location4, location5))
                .title("Midpoint 4 / 5")
                .draggable(true)
                .anchor(0.5f, 1f)
                .clusterable(true)
        )

        if (MobileServicesDetector.isHmsAvailable()) {
            map.getHuaweiMap()?.setMarkersClustering(true)
        }

        map.setOnMarkerClickListener(object : OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                map.animateCamera(CameraUpdateFactory.get().newLatLng(marker.position))
                marker.showInfoWindow()

                Toast.makeText(this@MapActivity, "Clicked ${marker.title}", Toast.LENGTH_SHORT)
                    .show()
                return true
            }
        })

        map.setOnCircleClickListener(object : OnCircleClickListener {
            override fun onCircleClick(circle: Circle) {
                Toast.makeText(this@MapActivity, "Clicked a circle!", Toast.LENGTH_SHORT).show()
            }
        })

        val descriptor: BitmapDescriptor? = BitmapDescriptorFactory.Provider.instance()
            .fromVector(this@MapActivity, R.drawable.ic_launcher_foreground)
        val go = GroundOverlayOptions.create()
            .position(LatLng(47.859026, 13.543000), 4000f, 4000f)
            .clickable(true)

        descriptor?.let {
            go.image(it)
        }

        map.addGroundOverlay(go)

        map.setOnGroundOverlayClickListener(object : OnGroundOverlayClickListener {
            override fun onGroundOverlayClickListener(groundOverlay: GroundOverlay) {
                Toast.makeText(this@MapActivity, "Clicked a groundOverlay!", Toast.LENGTH_SHORT)
                    .show()
            }
        })

        map.setInfoWindowAdapter(object : InfoWindowAdapter {
            override fun getInfoContents(marker: Marker?): View? {
                return null
            }

            override fun getInfoWindow(marker: Marker?): View {
                val view: View = layoutInflater.inflate(R.layout.infow_window_layout, null)

                val latLng = marker?.position

                val lat = view.findViewById<View>(R.id.info_window_lat) as TextView
                val lng = view.findViewById<View>(R.id.info_window_lng) as TextView

                lat.text = "Latitude:${latLng?.latitude}"
                lng.text = "Longitude:${latLng?.longitude}"

                return view
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mapDisposable.dispose()
    }
}