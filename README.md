<p>
    <img src="./documentation/img/logo-choiceSDK.png"/>
</p>

A SDK that aims to wrap similar functionalities from HMS and GMS services to one common interface. This is the launchpad for app providers expanding from GMS (Google Mobile Services) to HMS (Huawei Mobile Services) without having another codebranch to be setup, published and maintained. Choice SDK is already in use in multiple commercial, public and private operated apps. The goal is to save development, maintainance and security issues now and in future.

## Contents
- [Features](#features)
- [Installation](#installation)
  - [HMS / GMS specific](#hms--gms-specific)
  - [Choice SDK](#choice-sdk-1)
  - [Initialization](#initialization)
- [Usage](#usage)
  - [HMS Setup](#hms-setup)
  - [Analytics](#analytics)
  - [Location](#location)
  - [Maps](#maps)
  - [Messaging (Push Kit)](#messaging-push-kit)
  - [Sign-In](#sign-in)
- [Demo app setup](#demo-app-setup)
- [Links](#links)

## Features
The SDK currently supports the following kits/APIs:
- Analytics
- Location
- Maps
- Messaging
- Sign-In

## Installation

### HMS / GMS specific
To use HMS services in the first place, integration of the HMS core SDK is needed. Follow the steps mentioned in [How to set up the HMS core sdk](./documentation/hmscoresdksetup.md).   
As for the firebase integration just follow the official guide: https://firebase.google.com/docs/android/setup

The following plugins are required by the core of the SDK and need to be declared in your app.

`Project` gradle:
```gradle
buildscript{
    repositories {
        maven { url 'http://developer.huawei.com/repo/' }
    }

    dependencies {
        // GMS
        classpath 'com.google.gms:google-services:4.3.8'

        // HMS
        classpath 'com.huawei.agconnect:agcp:1.5.2.300'
    }
}

allprojects {
    repositories {
            maven {url 'http://developer.huawei.com/repo/'}
    }
}
```

`App` gradle:
```gradle
apply plugin: 'com.google.gms.google-services'
apply plugin: 'com.huawei.agconnect'
```

### Choice SDK
Add the following dependencies as required to your app. GMS, Firebase and HMS dependencies are included, so they do not need to be declared in your app.
```gradle
dependencies {
    def choicesdk_version = '0.3.0'
    implementation "at.bluesource.choicesdk:choicesdk-analytics:$choicesdk_version"
    implementation "at.bluesource.choicesdk:choicesdk-location:$choicesdk_version"
    implementation "at.bluesource.choicesdk:choicesdk-maps:$choicesdk_version"
    implementation "at.bluesource.choicesdk:choicesdk-messaging:$choicesdk_version"
    implementation "at.bluesource.choicesdk:choicesdk-signin:$choicesdk_version"
}
```

### Initialization
The Choice SDK needs the application context at start. You can provide the needed context by calling `ChoiceSdk.init(this)` in your application class:
```kotlin
class YourApp : Application() {
    override fun onCreate() {
        super.onCreate()

        ChoiceSdk.init(this)
    }
}
```

By default the SDK checks for GMS availability first. It is also possible to set the priority in which order the service availability is checked (for example if you prefer HMS services over GMS services on devices where both are available):
```kotlin
ChoiceSdk.init(this, listOf(MobileService.HMS, MobileService.GMS))
```

## Usage
Usually instances of SDK classes can be created by using their corresponding factory, for example `AnalyticsFactory.getAnalytics(this)`. These calls can throw an `UnsupportedOperationException` if neither GMS nor HMS services are available, so make sure to check beforehand.

The SDK might need some additional permissions, so add them if you see any Lint errors in your IDE.

**Important note:** The provided examples do not check for permissions! Add permission checks where needed.

Examples for each part are described in the documentation below.

### HMS Setup
[How to set up the HMS core SDK](./documentation/hmscoresdksetup.md)

### Analytics
[Analytics documentation](./documentation/analytics.md)

### Location
[Location documentation](./documentation/location.md)

### Maps
[Maps documentation](./documentation/map.md)

### Messaging (Push Kit)
[Messaging documentation](./documentation/messaging.md)

### Sign-In
[Sign-In documentation](./documentation/signin.md)

## Demo app setup
If you would like to use our demo app, you have to follow some steps to be able to build and run the project:
- Create a GMS and HMS project and download `google-services.json` and `agconnect-services.json` files into `ChoiceSDK/choicesdk-app`.
- In the app's `build.gradle` file replace `hmsProperties['HMS_APP_ID']` with your HMS app id, or create a `ChoiceSDK/choicesdk-app/hms.properties` file in with the following content and your HMS app id: `HMS_APP_ID=xxx`
- Change the `applicationId` of the app to the ID which you used to create the GMS/HMS projects.
- To build a release version replace our release signing config with your keystore properties.

## Links
- [Google APIs for Android](https://developers.google.com/android)
- [Huawei APIs](https://developer.huawei.com/consumer/en/hms)
- [RxJava](https://github.com/ReactiveX/RxJava)
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)