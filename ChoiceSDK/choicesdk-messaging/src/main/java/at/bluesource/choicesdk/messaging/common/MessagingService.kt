package at.bluesource.choicesdk.messaging.common

import android.content.Context
import at.bluesource.choicesdk.core.Outcome
import io.reactivex.rxjava3.core.Observable

/**
 * Common interface for gms and hms messaging service
 *
 * Base interface to retrieve messages from hms/gms Cloud Messaging.
 *
 * It provides functionality to automatically display notifications, and has methods that are invoked to give the status of upstream messages.
 * Use [at.bluesource.choicesdk.ChoiceSdk.getMessagingService] to retrieve an instance of [MessagingService]
 *
 * @see com.google.firebase.messaging.FirebaseMessagingService
 * @see com.huawei.hms.push.HmsMessageService
 */
interface MessagingService {
    fun requestToken(context: Context)
    suspend fun getToken(context: Context): String
    fun deleteToken(context: Context)
    fun getMessageReceivedObservable(): Observable<RemoteMessage>
    fun getMessageSentObservable(): Observable<String>
    fun getNewTokenObservable(): Observable<String>
    fun getOnSendErrorObservable(): Observable<Outcome<Exception>>
}