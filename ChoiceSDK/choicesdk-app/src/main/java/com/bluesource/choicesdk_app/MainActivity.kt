package com.bluesource.choicesdk_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import at.bluesource.choicesdk.core.MobileServicesDetector
import com.bluesource.choicesdk_app.analytics.AnalyticsActivity
import com.bluesource.choicesdk_app.biometrics.BiometricsActivity
import com.bluesource.choicesdk_app.location.LocationActivity
import com.bluesource.choicesdk_app.map.MapActivity
import com.bluesource.choicesdk_app.messages.MessagesActivity
import com.bluesource.choicesdk_app.signin.SignInActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        val txtView: TextView = findViewById(R.id.textView_main)
        val locationBtn: Button = findViewById(R.id.btn_activity_location)
        val mapBtn: Button = findViewById(R.id.btn_activity_map)
        val pushBtn: Button = findViewById(R.id.btn_activity_push)
        val biometricsBtn: Button = findViewById(R.id.btn_activity_biometrics)
        val signInBtn: Button = findViewById(R.id.btn_activity_sign_in)
        val analyticsBtn: Button = findViewById(R.id.btn_analytics)
        val crashBtn: Button = findViewById(R.id.btn_crash_it)
        val txtVersion: TextView = findViewById(R.id.txt_version)

        txtView.text = when {
            MobileServicesDetector.isGmsAvailable() -> "GMS detected | GMS demo"
            MobileServicesDetector.isHmsAvailable() -> "HMS detected | HMS demo"
            else -> "No GMS nor HMS service detected!"
        }

        locationBtn.setOnClickListener {
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }

        mapBtn.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        pushBtn.setOnClickListener {
            val intent = Intent(this, MessagesActivity::class.java)
            startActivity(intent)
        }

        biometricsBtn.setOnClickListener {
            val intent = Intent(this, BiometricsActivity::class.java)
            startActivity(intent)
        }

        signInBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        analyticsBtn.setOnClickListener {
            val intent = Intent(this, AnalyticsActivity::class.java)
            startActivity(intent)
        }

        crashBtn.setOnClickListener {
            throw RuntimeException("Intentional test crash")
        }

        txtVersion.text = "Version: ${BuildConfig.VERSION_NAME}"
    }
}
