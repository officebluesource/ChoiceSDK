package at.bluesource.choicesdk.messaging.common

import android.content.Context
import android.util.Log
import at.bluesource.choicesdk.core.ChoiceSdk
import at.bluesource.choicesdk.core.Outcome
import at.bluesource.choicesdk.messaging.factory.MessagingRepositoryFactory
import com.jakewharton.rxrelay3.BehaviorRelay
import com.jakewharton.rxrelay3.PublishRelay
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.*

/**
 * Common class for the messaging service to communicate with
 *
 * @see HmsMessagingService and GmsMessagingService
 */


internal class MessagingRepository : MessagingService {

    private var messageReceivedRelay: PublishRelay<RemoteMessage> = PublishRelay.create()
    private var messageSentRelay: PublishRelay<String> = PublishRelay.create()
    private var sendErrorRelay: PublishRelay<Outcome<Exception>> = PublishRelay.create()
    private var tokenRelay: BehaviorRelay<String> = BehaviorRelay.create()
    private val tokenProvider = MessagingRepositoryFactory.getTokenProvider()

    init {
        requestToken(ChoiceSdk.getContext())
    }

    override fun requestToken(context: Context) {
        Log.d("ChoiceSDK", "Request token")
        applicationScope.launch {
            val token = tokenProvider.requestToken(context)
            withContext(Dispatchers.Main) {
                tokenRelay.accept(token)
            }
        }
    }

    override suspend fun getToken(context: Context): String {
        return tokenProvider.requestToken(context)
    }

    override fun deleteToken(context: Context) {
        Log.d("ChoiceSDK", "Delete token")
        applicationScope.launch {
            tokenProvider.deleteToken(context)
            withContext(Dispatchers.Main) {
                tokenRelay.accept("")
            }
        }
    }

    override fun getMessageReceivedObservable(): Observable<RemoteMessage> {
        return messageReceivedRelay
    }

    override fun getMessageSentObservable(): Observable<String> {
        return messageSentRelay
    }

    override fun getNewTokenObservable(): Observable<String> {
        return tokenRelay
    }

    override fun getOnSendErrorObservable(): Observable<Outcome<Exception>> {
        return sendErrorRelay
    }

    fun onMessageReceived(remoteMessage: RemoteMessage) {
        messageReceivedRelay.accept(remoteMessage)
    }

    fun onMessageSent(messageId: String) {
        messageSentRelay.accept(messageId)
    }

    fun onSendError(failure: Outcome<Exception>) {
        sendErrorRelay.accept(failure)
    }

    fun onNewToken(token: String) {
        tokenRelay.accept(token)
    }

    companion object {
        private val applicationScope = CoroutineScope(SupervisorJob())

        @Volatile
        private var INSTANCE: MessagingRepository? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        fun getInstance(): MessagingRepository {
            return INSTANCE
                ?: synchronized(this) {
                    MessagingRepository().also {
                        INSTANCE = it
                    }
                }
        }
    }
}