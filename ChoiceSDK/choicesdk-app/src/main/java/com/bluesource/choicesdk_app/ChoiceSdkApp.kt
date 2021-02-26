package com.bluesource.choicesdk_app

import android.app.Application
import at.bluesource.choicesdk.core.ChoiceSdk

class ChoiceSdkApp : Application() {
    override fun onCreate() {
        super.onCreate()

        ChoiceSdk.init(this)
    }
}