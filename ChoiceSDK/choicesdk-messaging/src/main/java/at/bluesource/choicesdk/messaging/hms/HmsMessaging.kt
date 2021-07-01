package at.bluesource.choicesdk.messaging.hms

import android.content.Context
import at.bluesource.choicesdk.core.task.HmsTask
import at.bluesource.choicesdk.core.task.HmsTask.Companion.toHMSTask
import at.bluesource.choicesdk.core.task.Task
import at.bluesource.choicesdk.messaging.common.Messaging
import at.bluesource.choicesdk.messaging.common.RemoteMessage
import at.bluesource.choicesdk.messaging.common.RemoteMessage.Companion.toHmsRemoteMessage
import com.huawei.hms.push.HmsMessaging

/**
 * Wrapper class for hms version of Messaging
 *
 * @see com.huawei.hms.push.HmsMessaging
 */
internal class HmsMessaging(private var context: Context) : Messaging {

    override fun isAutoInitEnabled(): Boolean {
        return HmsMessaging.getInstance(context).isAutoInitEnabled
    }

    override fun setAutoInitEnabled(enable: Boolean) {
        HmsMessaging.getInstance(context).isAutoInitEnabled = enable
    }

    override fun send(message: RemoteMessage) {
        HmsMessaging.getInstance(context).send(message.toHmsRemoteMessage())
    }

    override fun subscribeToTopic(topic: String): Task<Void> {
        return HmsMessaging.getInstance(context).subscribe(topic).toHMSTask()
    }

    override fun unsubscribeFromTopic(topic: String): HmsTask<Void> {
        return HmsMessaging.getInstance(context).unsubscribe(topic).toHMSTask()
    }

    companion object {
        @Volatile
        private var INSTANCE: at.bluesource.choicesdk.messaging.hms.HmsMessaging? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        fun getInstance(context: Context): at.bluesource.choicesdk.messaging.hms.HmsMessaging {
            return INSTANCE
                ?: synchronized(this) {
                    at.bluesource.choicesdk.messaging.hms.HmsMessaging(context).also {
                        INSTANCE = it
                    }
                }
        }
    }
}