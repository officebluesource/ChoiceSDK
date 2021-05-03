package com.bluesource.choicesdk_app.analytics

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import at.bluesource.choicesdk.analytics.common.Analytics
import at.bluesource.choicesdk.analytics.factory.AnalyticsFactory
import com.bluesource.choicesdk_app.R
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver

class AnalyticsActivity : AppCompatActivity() {
    private val TAG = "Test"
    private lateinit var analytics: Analytics
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        analytics = AnalyticsFactory.getAnalytics(this)

        initViews()
    }

    private fun initViews() {
        val sendEventBtn = findViewById<Button>(R.id.button_log_event)
        val sendCurrentScreenBtn = findViewById<Button>(R.id.button_set_screen)
        val textView = findViewById<TextView>(R.id.textView_appInstanceId)

        sendEventBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("custom_key", "custom_value")
            analytics.logEvent("Demo_Analytics_Btn", bundle)
        }

        sendCurrentScreenBtn.setOnClickListener {
            analytics.setCurrentScreen(this, "Demo_Analytics_Screen", null)
        }

        val observer = object : DisposableObserver<String>() {
            override fun onComplete() {
                Log.d(TAG, "analytics onComplete")
            }

            override fun onNext(result: String) {
                textView.text = "AppInstanceId: $result"
            }

            override fun onError(e: Throwable?) {
                Log.d(TAG, "analytics onError ${e?.message}")
            }
        }
        disposables.add(observer)

        analytics.getAppInstanceIdObservable().subscribeWith(observer)
    }

    override fun onDestroy() {
        analytics.setCurrentScreenExit("Demo_Analytics_Screen")
        disposables.dispose()
        super.onDestroy()
    }
}