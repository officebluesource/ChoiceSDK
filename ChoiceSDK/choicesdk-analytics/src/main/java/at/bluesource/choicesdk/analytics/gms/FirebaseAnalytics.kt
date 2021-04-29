package at.bluesource.choicesdk.analytics.gms

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.annotation.RequiresPermission
import androidx.core.os.bundleOf
import at.bluesource.choicesdk.analytics.common.Analytics
import at.bluesource.choicesdk.core.task.GmsTask
import at.bluesource.choicesdk.core.task.Task
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable

/**
 * Wrapper class for firebase analytics
 *
 * @see com.google.firebase.analytics.FirebaseAnalytics
 */
internal class FirebaseAnalytics private constructor() : Analytics {
    private lateinit var firebaseAnalytics: com.google.firebase.analytics.FirebaseAnalytics
    private val appInstanceIdRelay: BehaviorRelay<String> = BehaviorRelay.create()

    override fun getAppInstanceIdObservable(): Observable<String> {
        getAppInstanceIdTask()
        return appInstanceIdRelay
    }

    override fun getAppInstanceIdTask(): Task<String> {
        return GmsTask<String>(firebaseAnalytics.appInstanceId.addOnSuccessListener {
            appInstanceIdRelay.accept(it)
        })
    }

    override suspend fun getAppInstanceId(): String? {
        return GmsTask<String>(firebaseAnalytics.appInstanceId).await()
    }

    override fun logEvent(name: String, params: Bundle?) {
        firebaseAnalytics.logEvent(name, params)
    }

    override fun resetAnalyticsData() {
        firebaseAnalytics.resetAnalyticsData()
    }

    override fun setAnalyticsCollectionEnabled(enabled: Boolean) {
        firebaseAnalytics.setAnalyticsCollectionEnabled(enabled)
    }

    override fun setCurrentScreen(
        activity: Activity,
        screenName: String,
        screenClassOverride: String?
    ) {
        val bundle = bundleOf(com.google.firebase.analytics.FirebaseAnalytics.Param.SCREEN_NAME to screenName)
        if (screenClassOverride != null) {
            bundle.putString(com.google.firebase.analytics.FirebaseAnalytics.Param.SCREEN_CLASS, screenClassOverride)
        }
        firebaseAnalytics.logEvent(com.google.firebase.analytics.FirebaseAnalytics.Event.SCREEN_VIEW, bundle)
    }

    override fun setSessionTimeoutDuration(milliseconds: Long) {
        firebaseAnalytics.setSessionTimeoutDuration(milliseconds)
    }

    override fun setUserID(id: String) {
        firebaseAnalytics.setUserId(id)
    }

    override fun setUserProperty(name: String, value: String) {
        firebaseAnalytics.setUserProperty(name, value)
    }

    override fun setCurrentScreenExit(screenName: String) {
        // empty; as firebase does not provide such functionality
    }

    override fun setPushToken(token: String) {
        // empty; as firebase does not provide such functionality
    }

    companion object {
        @Volatile
        private var INSTANCE: FirebaseAnalytics? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        @RequiresPermission(allOf = ["android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET", "android.permission.WAKE_LOCK"])
        fun getInstance(context: Context): FirebaseAnalytics {
            return INSTANCE
                ?: synchronized(this) {
                    FirebaseAnalytics().also {
                        INSTANCE = it
                        it.firebaseAnalytics =
                            com.google.firebase.analytics.FirebaseAnalytics.getInstance(context)
                    }
                }
        }
    }
}