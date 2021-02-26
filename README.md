<div align="center"><h1 align="center">Choice SDK</h1></div>

<div align="center">
  A SDK that aims to wrap similar functionalities from HMS and GMS services to one common interface.
</div>

<br />

<div align="center">
  <!-- Build Status -->
  <a href="">
    <img src="https://gitlab.bluesource.at/bs_projects/huawei/choicesdk/android/badges/develop/pipeline.svg?style=flat-square"
      alt="Build Status" />
  </a>
  <!-- Test Coverage -->
  <a href="">
    <img src="https://gitlab.bluesource.at/bs_projects/huawei/choicesdk/android/badges/develop/coverage.svg?style=flat-square"
      alt="Test Coverage" />
  </a>
  <!-- Latest version 
  <a href="">
    <img src=""
      alt="Latest version" />
  </a>-->
</div>

<div align="center">
  <h3>
    <a href="https://developer.huawei.com/consumer/en/doc/10123">
      HMS Reference
    </a>
    <span> | </span>
    <a href="https://developers.google.com/android">
      GMS Reference
    </a>
    <span> | </span>
    <a href="https://gitlab.bluesource.at/bs_projects/huawei/choicesdk/android/-/tree/master/ChoiceSDK/choicesdk-app">
      Sample app
    </a>
  
  </h3>
</div>

# Contents
- [Features](#features)
- [Installation](#installation)
  - [HMS / GMS specific](#hms-gms-specific)
  - [Choice SDK](#choice-sdk-1)
  - [Initialization](#initialization)
- [Usage](#usage)
  - [HMS Setup](#hms-setup)
  - [Analytics](#analytics)
  - [Location](#location)
  - [Maps](#maps)
  - [Messaging](#messaging)
  - [Signin](#signin)
- [Links](#links)

# Features
The SDK currently supports the following kits/APIs:
- Analytics
- Location
- Maps
- Messaging
- Sign in

# Installation

## HMS / GMS specific
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
        classpath 'com.google.gms:google-services:4.3.3'

        // HMS
        classpath 'com.huawei.agconnect:agcp:1.4.1.300'
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

## Choice SDK
Add the following dependencies as required to your app. GMS, Firebase and HMS dependencies are included, so they do not need to be declared in your app.
```gradle
dependencies {
    implementation 'at.bluesource.choicesdk:choicesdk-analytics:0.1.0'
    implementation 'at.bluesource.choicesdk:choicesdk-location:0.1.0'
    implementation 'at.bluesource.choicesdk:choicesdk-maps:0.1.0'
    implementation 'at.bluesource.choicesdk:choicesdk-messaging:0.1.0'
    implementation 'at.bluesource.choicesdk:choicesdk-signin:0.1.0'
}
```

## Initialization
The Choice SDK needs the application context at start. You can provide the needed context by calling `ChoiceSdk.init(this)` in your application class:
```kotlin
class YourApp : Application() {
    override fun onCreate() {
        super.onCreate()

        ChoiceSdk.init(this)
    }
}
```

# Usage
Usually instances of SDK classes can be created by using their corresponding factory, for example `AnalyticsFactory.getAnalytics(this)`. These calls can throw an `UnsupportedOperationException` if neither GMS nor HMS services are available, so make sure to check beforehand.

The SDK might need some additional permissions, so add them if you see any Lint errors in your IDE.

**Important note:** The provided examples do not check for permissions! Add permission checks where needed.

Examples for each part are described in the documentation below.

## HMS Setup
[How to set up the HMS core SDK](./documentation/hmscoresdksetup.md)

## Analytics
[Analytics documentation](./documentation/analytics.md)

## Location
[Location documentation](./documentation/location.md)

## Maps
[Maps documentation](./documentation/map.md)

## Messaging
[Messaging documentation](./documentation/messaging.md)

## Signin
[Signin documentation](./documentation/signin.md)

# Links
- [RxJava](https://github.com/ReactiveX/RxJava)
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Google APIs for Android](https://developers.google.com/android)
- [Huawei APIs](https://developer.huawei.com/consumer/en/hms)