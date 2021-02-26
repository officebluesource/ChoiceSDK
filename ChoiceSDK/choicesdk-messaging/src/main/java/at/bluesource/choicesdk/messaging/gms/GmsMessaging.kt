package at.bluesource.choicesdk.messaging.gms

import at.bluesource.choicesdk.core.task.GmsTask.Companion.toGMSTask
import at.bluesource.choicesdk.core.task.Task
import at.bluesource.choicesdk.messaging.common.Messaging
import at.bluesource.choicesdk.messaging.common.RemoteMessage
import at.bluesource.choicesdk.messaging.common.RemoteMessage.Companion.toGmsRemoteMessage
import com.google.firebase.messaging.FirebaseMessaging

/**
 * Wrapper class for gms version of Messaging (Firebase)
 *
 * @see com.google.firebase.messaging.FirebaseMessaging
 */
internal class GmsMessaging :
    Messaging {

    override fun isAutoInitEnabled(): Boolean {
        return FirebaseMessaging.getInstance().isAutoInitEnabled
    }

    override fun setAutoInitEnabled(enable: Boolean) {
        FirebaseMessaging.getInstance().isAutoInitEnabled = enable
    }

    override fun send(message: RemoteMessage) {
        FirebaseMessaging.getInstance().send(message.toGmsRemoteMessage())
    }

    override fun subscribeToTopic(topic: String): Task<Void> {
        return FirebaseMessaging.getInstance().subscribeToTopic(topic).toGMSTask()
    }

    override fun unsubscribeFromTopic(topic: String): Task<Void> {
        return FirebaseMessaging.getInstance().unsubscribeFromTopic(topic).toGMSTask()
    }

    companion object {
        @Volatile
        private var INSTANCE: GmsMessaging? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        fun getInstance(): GmsMessaging {
            return INSTANCE
                ?: synchronized(this) {
                    GmsMessaging().also {
                        INSTANCE = it
                    }
                }
        }
    }
}