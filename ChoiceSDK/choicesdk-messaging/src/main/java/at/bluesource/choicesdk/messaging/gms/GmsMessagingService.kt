package at.bluesource.choicesdk.messaging.gms

import at.bluesource.choicesdk.core.Outcome
import at.bluesource.choicesdk.messaging.common.MessagingRepository
import at.bluesource.choicesdk.messaging.common.RemoteMessage.Companion.toChoiceRemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService

/**
 * GMS message service to relay messages/errors
 */
internal class GmsMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: com.google.firebase.messaging.RemoteMessage) {
        MessagingRepository.getInstance().onMessageReceived(remoteMessage.toChoiceRemoteMessage())
    }

    override fun onMessageSent(messageId: String) {
        MessagingRepository.getInstance().onMessageSent(messageId)
    }

    override fun onSendError(messageId: String, exception: Exception) {
        MessagingRepository.getInstance().onSendError(
            Outcome.Failure(
                exception,
                messageId
            ) as Outcome<Exception>
        )
    }

    override fun onNewToken(token: String) {
        MessagingRepository.getInstance().onNewToken(token)
    }
}