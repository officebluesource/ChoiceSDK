

package at.bluesource.choicesdk.messaging.common

import at.bluesource.choicesdk.messaging.common.Notification.Companion.toChoiceNotification

/**
 * Common interface for hms and gms remote message
 *
 * Messages will be received via [MessagingService.getMessageReceivedObservable] and can be sent via [Messaging.send].
 * Messages may have a [RemoteMessage.notification] instance.
 *
 * Use the [RemoteMessage.Builder] class for building message instances to send via [Messaging.send].
 *
 * @see [com.google.firebase.messaging.RemoteMessage]
 * @see [com.huawei.hms.push.RemoteMessage]
 */
interface RemoteMessage {

    val collapseKey: String?
    val data: Map<String, String>
    val from: String?
    val to: String?
    val messageId: String?
    val messageType: String?
    val notification: Notification?
    val originalPriority: Int
    val priority: Int
    val ttl: Int
    val sentTime: Long

    @Suppress("unused")
    class Builder(var to: String) {
        private var collapseKey: String? = null
        private var data: MutableMap<String, String> = mutableMapOf()
        private var messageId: String? = null
        private var messageType: String? = null
        private var ttl: Int = 0

        fun addData(key: String, value: String): Builder {
            data[key] = value
            return this
        }

        fun build(): RemoteMessage {
            return object :
                RemoteMessage {
                override val collapseKey: String?
                    get() = this@Builder.collapseKey
                override val data: Map<String, String>
                    get() = this@Builder.data
                override val from: String?
                    get() = null
                override val to: String?
                    get() = this@Builder.to
                override val messageId: String?
                    get() = this@Builder.messageId
                override val messageType: String?
                    get() = this@Builder.messageType
                override val notification: Notification?
                    get() = null
                override val originalPriority: Int
                    get() = 0
                override val priority: Int
                    get() = 0
                override val ttl: Int
                    get() = this@Builder.ttl
                override val sentTime: Long
                    get() = 0
            }
        }

        fun clearData(): Builder {
            data.clear()
            return this
        }

        fun setCollapseKey(collapseKey: String): Builder {
            this.collapseKey = collapseKey
            return this
        }

        fun setData(data: Map<String, String>): Builder {
            this.data = data.toMutableMap()
            return this
        }

        fun setMessageId(messageId: String): Builder {
            this.messageId = messageId
            return this
        }

        fun setMessageType(messageType: String): Builder {
            this.messageType = messageType
            return this
        }

        fun setTtl(ttl: Int): Builder {
            this.ttl = ttl
            return this
        }
    }

    companion object {

        /**
         * Create [RemoteMessage] object from [com.google.firebase.messaging.RemoteMessage]
         */
        internal fun com.google.firebase.messaging.RemoteMessage.toChoiceRemoteMessage(): RemoteMessage {
            return object :
                RemoteMessage {
                override val collapseKey: String?
                    get() = this@toChoiceRemoteMessage.collapseKey
                override val data: Map<String, String>
                    get() = this@toChoiceRemoteMessage.data
                override val from: String?
                    get() = this@toChoiceRemoteMessage.from
                override val to: String?
                    get() = this@toChoiceRemoteMessage.to
                override val messageId: String?
                    get() = this@toChoiceRemoteMessage.messageId
                override val messageType: String?
                    get() = this@toChoiceRemoteMessage.messageType
                override val notification: Notification?
                    get() = this@toChoiceRemoteMessage.notification?.toChoiceNotification()
                override val originalPriority: Int
                    get() = this@toChoiceRemoteMessage.originalPriority
                override val priority: Int
                    get() = this@toChoiceRemoteMessage.priority
                override val ttl: Int
                    get() = this@toChoiceRemoteMessage.ttl
                override val sentTime: Long
                    get() = this@toChoiceRemoteMessage.sentTime
            }
        }

        /**
         * Create [RemoteMessage] object from [com.huawei.hms.push.RemoteMessage]
         */
        internal fun com.huawei.hms.push.RemoteMessage.toChoiceRemoteMessage(): RemoteMessage {
            return object :
                RemoteMessage {
                override val collapseKey: String?
                    get() = this@toChoiceRemoteMessage.collapseKey
                override val data: Map<String, String>
                    get() = this@toChoiceRemoteMessage.dataOfMap
                override val from: String?
                    get() = this@toChoiceRemoteMessage.from
                override val to: String?
                    get() = this@toChoiceRemoteMessage.to
                override val messageId: String?
                    get() = this@toChoiceRemoteMessage.messageId
                override val messageType: String?
                    get() = this@toChoiceRemoteMessage.messageType
                override val notification: Notification?
                    get() = this@toChoiceRemoteMessage.notification?.toChoiceNotification()
                override val originalPriority: Int
                    get() = this@toChoiceRemoteMessage.originalUrgency
                override val priority: Int
                    get() = this@toChoiceRemoteMessage.urgency
                override val ttl: Int
                    get() = this@toChoiceRemoteMessage.ttl
                override val sentTime: Long
                    get() = this@toChoiceRemoteMessage.sentTime
            }
        }

        internal fun RemoteMessage.toGmsRemoteMessage(): com.google.firebase.messaging.RemoteMessage {
            return com.google.firebase.messaging.RemoteMessage.Builder(this.to ?: "")
                .setData(this.data)
                .setCollapseKey(this.collapseKey)
                .setMessageId(this.messageId ?: "")
                .setMessageType(this.messageType)
                .setTtl(this.ttl)
                .build()
        }

        internal fun RemoteMessage.toHmsRemoteMessage(): com.huawei.hms.push.RemoteMessage {
            return com.huawei.hms.push.RemoteMessage.Builder(this.to ?: "")
                .setData(this.data)
                .setCollapseKey(this.collapseKey)
                .setMessageId(this.messageId ?: "")
                .setMessageType(this.messageType)
                .setTtl(this.ttl)
                .build()
        }
    }
}