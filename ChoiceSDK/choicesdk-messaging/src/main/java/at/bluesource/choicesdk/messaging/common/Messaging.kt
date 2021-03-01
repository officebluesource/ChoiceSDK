package at.bluesource.choicesdk.messaging.common

import at.bluesource.choicesdk.core.task.Task

/**
 * Common interface for gms and hms messaging
 *
 * Cloud Messaging singleton that provides methods for subscribing to topics and sending upstream messages.
 * Use [at.bluesource.choicesdk.ChoiceSdk.getMessageSender] to retrieve an instance of [Messaging]
 *
 * To process messages, use the [MessagingService] class methods to handle any events required by the application.
 *
 * @see com.google.firebase.messaging.FirebaseMessaging
 * @see com.huawei.hms.push.HmsMessaging
 */
interface Messaging {

    fun isAutoInitEnabled(): Boolean
    fun setAutoInitEnabled(enable: Boolean)
    fun send(message: RemoteMessage)
    fun subscribeToTopic(topic: String): Task<Void>
    fun unsubscribeFromTopic(topic: String): Task<Void>
}