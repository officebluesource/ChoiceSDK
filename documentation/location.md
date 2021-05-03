# Location

Getting the current location is fairly simple but needs some preparation beforehand. The preferred way is to request an `Observable<Outcome<LocationResult>>` as the callback and the listeners are handled by the specific instance of `FusedLocationProviderClient`. Note: The `Outcome` class has two different variations: `Outcome.Success` and `Outcome.Failure`. Their purpose should be self-explanatory. To get the value of a success use the `.value` property. For failures use the `.error` and `.extraMessage` properties.

LocationRequest:  
By default the `LocationRequest` has the following settings
- interval of `5000 milliseconds`
- fastest interval -> `interval / 6`
- priority -> `PRIORITY_HIGH_ACCURACY`
- numUpdates and expirationDuration -> `MAX_VALUE` (basically never expires)  
  
If different values are needed you can use the `LocationRequest.Builder()` to set the values.

## Setup
To request location updates the following permissions should be checked and added to the `AndroidManifest.xml` file:  
```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```
And if needed (API level >=29):  
```xml
<uses-permission android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
```

## Usage

Get a FusedLocationProviderClient instance:
```kotlin
val client: FusedLocationProviderClient = FusedLocationProviderFactory.getFusedLocationProviderClient(this)
```

Get last location:
```kotlin
client.getLastLocation()
    .addOnSuccessListener { location ->
        if (location != null) {
            ...
        }
    }
```

Request location updates:
```kotlin
private val disposables: CompositeDisposable = CompositeDisposable()

val observer: DisposableObserver<Outcome<LocationResult>> =
    object : DisposableObserver<Outcome<LocationResult>>() {
        override fun onNext(result: Outcome<LocationResult>) {
            when (result) {
                is Outcome.Success -> {
                    val locations: List<Location> = result.value.locations
                }
                is Outcome.Failure -> {
                    Log.e(TAG, "Error observed: ${result.extraMessage}", result.error)
                }
            }
        }

        override fun onError(e: Throwable?) {}
        override fun onComplete() {}
    }

val locationRequest: LocationRequest = LocationRequest.Builder()
    .setInterval(5000)
    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    .build()

val disposable = client.observeLocation(locationRequest)
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeWith(observer)

disposables.add(disposable)
```

Be sure to dispose all observer and remove location updates!
```kotlin
override fun onDestroy() {
    disposables.dispose() // if you used an observable
    client.stopLocationUpdates()
    super.onDestroy()
}
```

Check location settings:
```kotlin
val settingsClient: SettingsClient = SettingsClientFactory.getSettingsClient(this)

val locationRequest: LocationRequest = LocationRequest.Builder()
    .setInterval(5000)
    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    .build()

val locationSettingsRequest: LocationSettingsRequest = LocationSettingsRequest.Builder()
    .addLocationRequest(locationRequest)
    .build()

settingsClient.checkLocationSettings(locationSettingsRequest)
    .addOnSuccessListener { result ->
        val locationUsable = result?.getLocationSettingsStates()?.isLocationUsable
    }
```

## Links
- [HMS Location](https://developer.android.com/training/location/request-updates)
- [GMS Location](https://developer.huawei.com/consumer/en/hms/huawei-locationkit)