package at.bluesource.choicesdk.messaging.hms

import at.bluesource.choicesdk.core.Outcome
import at.bluesource.choicesdk.messaging.common.MessagingRepository
import at.bluesource.choicesdk.messaging.common.RemoteMessage.Companion.toChoiceRemoteMessage


/**
 * HMS message service to relay messages/errors
 */
internal class HmsMessagingService : com.huawei.hms.push.HmsMessageService() {

    override fun onMessageReceived(message: com.huawei.hms.push.RemoteMessage?) {
        if (message != null) {
            MessagingRepository.getInstance().onMessageReceived(message.toChoiceRemoteMessage())
        }
    }

    override fun onMessageSent(messageId: String?) {
        messageId?.let {
            MessagingRepository.getInstance().onMessageSent(it)
        }
    }

    override fun onSendError(messageId: String?, exception: Exception?) {
        if (messageId != null && exception != null) {
            val failure = Outcome.Failure(
                exception,
                messageId
            ) as Outcome<Exception>
            MessagingRepository.getInstance().onSendError(failure)
        }
    }

    override fun onNewToken(token: String?) {
        token?.let {
            MessagingRepository.getInstance().onNewToken(it)
        }
    }
}