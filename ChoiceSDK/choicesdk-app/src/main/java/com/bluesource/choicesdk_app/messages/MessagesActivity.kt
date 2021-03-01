package com.bluesource.choicesdk_app.messages

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.messaging.common.MessagingService
import at.bluesource.choicesdk.messaging.common.RemoteMessage
import at.bluesource.choicesdk.messaging.factory.MessagingFactory
import at.bluesource.choicesdk.messaging.factory.MessagingRepositoryFactory
import com.bluesource.choicesdk_app.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


class MessagesActivity : AppCompatActivity() {
    private lateinit var textViewPush: TextView
    private lateinit var textViewToken: TextView
    private lateinit var messagingService: MessagingService
    private lateinit var token: String
    private val disposables = CompositeDisposable()
    private lateinit var notificationApi: NotificationApi

    companion object {
        private const val CHANNEL_ID = "Group1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)
        messagingService = MessagingRepositoryFactory.getMessagingService()
        createNotificationChannel()
        initViews()
        initApi()
        val tokenObserver = object : DisposableObserver<String>() {
            override fun onComplete() {
                Toast.makeText(this@MessagesActivity, "Observable completed", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onNext(result: String) {
                textViewToken.text = result
                token = result
                Log.d("ChoiceSDK", result)
            }

            override fun onError(e: Throwable?) {
                Toast.makeText(this@MessagesActivity, "Observable error", Toast.LENGTH_LONG).show()
            }
        }

        val messageObserver: DisposableObserver<RemoteMessage> = object : DisposableObserver<RemoteMessage>() {
            override fun onComplete() {
                Toast.makeText(this@MessagesActivity, "Observable completed", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onNext(result: RemoteMessage) {
                textViewPush.text = "${textViewPush.text}\n\nfrom: ${result.from} \ndata:${result.data}\npriority:${result.priority}\ntitle: ${result.notification?.title}\ntype: ${result.messageType}"
                showNotification(result)
            }

            override fun onError(e: Throwable?) {
                Toast.makeText(this@MessagesActivity, "Observable error", Toast.LENGTH_LONG).show()
            }
        }

        disposables.addAll(tokenObserver, messageObserver)

        messagingService.getNewTokenObservable().subscribeWith(tokenObserver)
        messagingService.getMessageReceivedObservable().observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(messageObserver)
    }


    private fun initViews() {
        textViewPush = findViewById(R.id.textView_push)
        textViewToken = findViewById(R.id.textView_token)
        val btnToken = findViewById<Button>(R.id.button_get_token)
        val btnSubscribe = findViewById<Button>(R.id.button_subscribe)
        val btnDeleteToken = findViewById<Button>(R.id.button_delete_token)
        val btnTriggerNotification = findViewById<Button>(R.id.button_trigger_notification)
        textViewPush.movementMethod = ScrollingMovementMethod()

        val txtPushServiceType = findViewById<TextView>(R.id.textView_push_service_type)
        txtPushServiceType.text = when {
            MobileServicesDetector.isGmsAvailable() -> "Using GSM Push Service"
            MobileServicesDetector.isHmsAvailable() -> "Using HSM Push Service"
            else -> ""
        }

        btnToken.setOnClickListener { getToken() }
        btnSubscribe.setOnClickListener { subscribeToTopic() }
        btnDeleteToken.setOnClickListener { deleteToken() }
        btnTriggerNotification.setOnClickListener { triggerNotification() }
    }

    private fun subscribeToTopic() {
        MessagingFactory.getMessaging().subscribeToTopic("News")
    }

    private fun getToken() {
        messagingService.requestToken(this)
    }

    private fun deleteToken() {
        messagingService.deleteToken(this)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun showNotification(notification: RemoteMessage) {
        val builder = NotificationCompat.Builder(this@MessagesActivity, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_notification))
            .setContentTitle(notification.notification?.title)
            .setContentText(notification.notification?.body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(applicationContext)) {
            // notificationId is a unique int for each notification that you must define
            notify(1, builder.build())
        }
    }

    private fun initApi() {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://huawei.bluesource.at/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(httpClient.build())
            .build()
        notificationApi = retrofit.create(NotificationApi::class.java)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_ID
            val descriptionText = "Description of $CHANNEL_ID"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun getServiceType(): String {
        if (MobileServicesDetector.isGmsAvailable()) {
            return "google"
        } else if (MobileServicesDetector.isHmsAvailable()) {
            return "huawei"
        }
        return ""
    }

    private fun triggerNotification() {
        GlobalScope.launch {
            try {
                val response: String = notificationApi.triggerNotification(getServiceType(), token)
                Log.d("ChoiceSDK", "Response: $response")
            } catch (e: Exception) {
                Log.d("ChoiceSDK", "Error while sending request")
            }
        }
    }
}