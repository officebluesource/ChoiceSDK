# Analytics

Analytics are fairly simple as the main logging happens in the background. HMS has some extra functionality where the page exit can be set as well, this might be needed to get improved analytics accuracy.

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