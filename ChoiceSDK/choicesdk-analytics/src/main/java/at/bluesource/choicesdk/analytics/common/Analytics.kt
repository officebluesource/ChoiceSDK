package at.bluesource.choicesdk.analytics.common

import android.app.Activity
import android.os.Bundle
import at.bluesource.choicesdk.core.task.Task
import io.reactivex.rxjava3.core.Observable

/**
 * Common interface for hms and gms analytics
 *
 * Hms version allows to set the screen exit and push token as well
 *
 * @see at.bluesource.choicesdk.analytics.gms.FirebaseAnalytics
 * @see at.bluesource.choicesdk.analytics.hms.HuaweiAnalytics
 */
interface Analytics {
    fun getAppInstanceIdObservable(): Observable<String>
    fun getAppInstanceId(): Task<String>
    fun logEvent(name: String, params: Bundle?)
    fun resetAnalyticsData()
    fun setAnalyticsCollectionEnabled(enabled: Boolean)
    fun setCurrentScreen(activity: Activity, screenName: String, screenClassOverride: String?)
    fun setSessionTimeoutDuration(milliseconds: Long)
    fun setUserID(id: String)
    fun setUserProperty(name: String, value: String)

    /**
     * HMS only
     */
    // before this is called paged start has to be called, otherwise it will be inaccurate
    fun setCurrentScreenExit(screenName: String)
    fun setPushToken(token: String)
}