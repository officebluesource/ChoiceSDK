# Analytics

Analytics are fairly simple as the main logging happens in the background. HMS has some extra functionality where the page exit can be set as well, this might be needed to get improved analytics accuracy.

## Additional setup for "Advanced Analytics Service"

We recommend to enabled "Advanced Analytics Service" in AppGallery Connect (under "HUAWEI Analytics"). Please make sure to re-download `agconnect-services.json` after enabling it and add additional Gradle dependencies:

`Project` gradle:
```gradle
buildscript {
    dependencies {
        classpath 'com.huawei.hms.plugin:analytics:5.0.1.300'
    }
}
```

`App` gradle:
```gradle
apply plugin: 'com.huawei.hms.plugin.analytics'

dependencies {
    implementation 'com.huawei.hms:hianalytics:5.0.1.300'
}
```

## Usage

Getting the analytics instance:
```kotlin
val analytics: Analytics = AnalyticsFactory.getAnalytics(this)
```

Sending custom events:
```kotlin
val bundle: Bundle = Bundle()
bundle.putString("custom_key", "custom_value")
analytics.logEvent("Demo_Analytics_Btn", bundle)
```

Setting current screen:
```kotlin
analytics.setCurrentScreen(this, "Demo_Analytics_Screen", null)
```

Setting a user property:
```kotlin
analytics.setUserProperty("loves_tea", "definitely")
```

## Links
- [GMS (Firebase) Analytics](https://firebase.google.com/docs/analytics/get-started?platform=android)
- [HMS Analytics](https://developer.huawei.com/consumer/en/hms/huawei-analyticskit)