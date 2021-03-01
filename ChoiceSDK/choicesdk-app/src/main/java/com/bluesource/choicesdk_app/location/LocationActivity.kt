package com.bluesource.choicesdk_app.location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import at.bluesource.choicesdk.core.Outcome
import at.bluesource.choicesdk.core.task.listener.OnFailureListener
import at.bluesource.choicesdk.core.task.listener.OnSuccessListener
import at.bluesource.choicesdk.location.common.*
import at.bluesource.choicesdk.location.factory.FusedLocationProviderFactory
import at.bluesource.choicesdk.location.factory.SettingsClientFactory
import com.bluesource.choicesdk_app.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver

class LocationActivity : AppCompatActivity() {
    private val TAG = "Test"
    private lateinit var locationCallback: LocationCallback
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationTextView: TextView
    private lateinit var settingsClient: SettingsClient
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        locationCallback = object : LocationCallback {
            override fun onLocationResult(result: LocationResult?) {
                result?.let {
                    val locations: List<Location> = result.locations
                    locations.forEach {
                        val message =
                            "Mockmode on: ${it.isFromMockProvider}\nLocation [latitude, longitude, accuracy]: ${it.latitude}; ${it.longitude}; ${it.accuracy}"
                        Log.d(TAG, message)
                        locationTextView.text = message
                    }
                }
            }

            override fun onLocationAvailability(locationAvailability: LocationAvailability?) {
                locationAvailability?.let {
                    if (!it.isLocationAvailable) {
                        Toast.makeText(
                            this@LocationActivity,
                            "Location is not available",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Log.d(
                        TAG,
                        "onLocationAvailability isLocationAvailable: ${it.isLocationAvailable}"
                    )
                }
            }
        }

        initViews()
        fusedLocationProviderClient = FusedLocationProviderFactory.getFusedLocationProviderClient(this)
        settingsClient = SettingsClientFactory.getSettingsClient(this)
    }

    private fun initViews() {
        val buttonRequest = findViewById<Button>(R.id.button_request)
        val buttonRemove = findViewById<Button>(R.id.button_remove)
        val buttonMock = findViewById<Button>(R.id.button_mock)
        locationTextView = findViewById(R.id.textView2)

        buttonRequest.setOnClickListener { requestLocationUpdatesObservable() }
        buttonRemove.setOnClickListener { removeLocationUpdates() }
        buttonMock.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationProviderClient.setMockMode(true)
                val location = Location("mock")
                location.latitude = 47.859026
                location.longitude = 13.543000
                fusedLocationProviderClient.setMockLocation(location)
                    .addOnFailureListener(object : OnFailureListener {
                        override fun onFailure(e: Exception) {
                            when (e) {
                                is at.bluesource.choicesdk.core.exception.ApiException -> {
                                    Toast.makeText(
                                        this@LocationActivity,
                                        e.message,
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                            Log.w(TAG, e.message ?: "")
                        }
                    })
            }
        }
    }

    private fun removeLocationUpdates() {
        try {
            locationTextView.text = ""
            fusedLocationProviderClient.stopLocationUpdates()
            disposables.clear()
        } catch (e: Exception) {
            Log.d(TAG, "failed to remove location updates: ${e.message}")
        }
    }

    private fun requestLocationUpdatesObservable() {
        if (!checkPermissions()) return

        val locationRequest: LocationRequest = LocationRequest.createDefault()
        val locationSettingsRequest: LocationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        val observer: DisposableObserver<Outcome<LocationResult>> =
            object : DisposableObserver<Outcome<LocationResult>>() {
                override fun onComplete() {
                    Log.d(TAG, "Observe completed")
                }

                override fun onNext(result: Outcome<LocationResult>) {
                    when (result) {
                        is Outcome.Success -> {

                            val locations: List<Location> = result.value.locations
                            locations.forEach { location ->
                                val message =
                                    "Location [longitude, latitude, accuracy]: ${location.longitude}; ${location.latitude}; ${location.accuracy}"
                                Log.d(TAG, message)
                                locationTextView.text = message
                            }
                        }
                        is Outcome.Failure -> {
                            if (result.extraMessage != null) {
                                Toast.makeText(
                                    this@LocationActivity,
                                    result.extraMessage,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                            Log.e(TAG, "Observe error: ${result.extraMessage}", result.error)
                            removeLocationUpdates()
                        }
                    }
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "Observe error: ${e?.message}", e)
                }
            }

        settingsClient.checkLocationSettings(locationSettingsRequest)
            .addOnSuccessListener(object : OnSuccessListener<LocationSettingsResponse?> {
                override fun onSuccess(result: LocationSettingsResponse?) {
                    if (ActivityCompat.checkSelfPermission(
                            this@LocationActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            this@LocationActivity,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }
                    val disposable =
                        fusedLocationProviderClient.observeLocation(locationRequest)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(observer)
                    disposables.add(disposable)
                }
            })
    }

    private fun requestLocationUpdatesWithCallback() {
        if (!checkPermissions()) return

        val locationRequest: LocationRequest = LocationRequest.Builder()
            .setInterval(5000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .build()

        val locationSettingsRequest: LocationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        settingsClient.checkLocationSettings(locationSettingsRequest)
            .addOnSuccessListener(object : OnSuccessListener<LocationSettingsResponse?> {
                override fun onSuccess(result: LocationSettingsResponse?) {

                    if (ActivityCompat.checkSelfPermission(
                            applicationContext,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            applicationContext,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        return
                    }
                    fusedLocationProviderClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        Looper.getMainLooper()
                    ).addOnSuccessListener(object : OnSuccessListener<Void?> {
                        override fun onSuccess(result: Void?) {
                            Log.i(TAG, "requestLocationUpdatesWithCallback onSuccess")
                        }
                    }).addOnFailureListener(object : OnFailureListener {
                        override fun onFailure(e: Exception) {
                            Log.i(TAG, "requestLocationUpdatesWithCallback failure")
                        }
                    })
                }
            })
            .addOnFailureListener(object : OnFailureListener {
                override fun onFailure(e: Exception) {
                    Log.e(TAG, "checkLocationSetting onFailure:  ${e.message}")
                }
            })
    }


    private fun checkPermissions(): Boolean {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val strings = arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

                ActivityCompat.requestPermissions(this, strings, 1)
                return false
            }
        } else {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val strings = arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
                )

                ActivityCompat.requestPermissions(this, strings, 2)
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.size > 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                requestLocationUpdatesObservable()
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
            }
        } else if (requestCode == 2) {
            if (grantResults.size > 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED
            ) {
                requestLocationUpdatesObservable()
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        disposables.dispose()
        removeLocationUpdates()
        super.onDestroy()
    }
}