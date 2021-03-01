package at.bluesource.choicesdk.analytics.hms

import android.app.Activity
import android.content.Context
import android.os.Bundle
import at.bluesource.choicesdk.analytics.common.Analytics
import at.bluesource.choicesdk.core.task.HmsTask
import at.bluesource.choicesdk.core.task.Task
import com.huawei.hms.analytics.HiAnalytics
import com.huawei.hms.analytics.HiAnalyticsInstance
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable

/**
 * Wrapper class for huawei analytics
 *
 * @see com.huawei.hms.analytics.HiAnalyticsInstance
 */
internal class HuaweiAnalytics private constructor() : Analytics {
    private lateinit var hiAnalytics: HiAnalyticsInstance
    private val appInstanceIdRelay: BehaviorRelay<String> = BehaviorRelay.create()

    override fun getAppInstanceIdObservable(): Observable<String> {
        getAppInstanceId()
        return appInstanceIdRelay
    }

    override fun getAppInstanceId(): Task<String> {
        return HmsTask<String>(hiAnalytics.aaid.addOnSuccessListener {
            appInstanceIdRelay.accept(it)
        })
    }

    override fun logEvent(name: String, params: Bundle?) {
        hiAnalytics.onEvent(name, params)
    }

    override fun resetAnalyticsData() {
        hiAnalytics.clearCachedData()
    }

    override fun setAnalyticsCollectionEnabled(enabled: Boolean) {
        hiAnalytics.setAnalyticsEnabled(enabled)
    }

    override fun setCurrentScreen(
        activity: Activity,
        screenName: String,
        screenClassOverride: String?
    ) {
        hiAnalytics.pageStart(screenName, screenClassOverride)
    }

    override fun setSessionTimeoutDuration(milliseconds: Long) {
        hiAnalytics.setSessionDuration(milliseconds)
    }

    override fun setUserID(id: String) {
        hiAnalytics.setUserId(id)
    }

    override fun setUserProperty(name: String, value: String) {
        hiAnalytics.setUserProfile(name, value)
    }

    override fun setCurrentScreenExit(screenName: String) {
        hiAnalytics.pageEnd(screenName)
    }

    override fun setPushToken(token: String) {
        hiAnalytics.setPushToken(token)
    }

    companion object {
        @Volatile
        private var INSTANCE: HuaweiAnalytics? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        fun getInstance(context: Context): HuaweiAnalytics {
            return INSTANCE
                ?: synchronized(this) {
                    HuaweiAnalytics().also {
                        INSTANCE = it
                        it.hiAnalytics = HiAnalytics.getInstance(context)
                    }
                }
        }
    }
}