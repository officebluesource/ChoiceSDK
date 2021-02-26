package at.bluesource.choicesdk.messaging.common

import android.net.Uri

/**
 * Common interface for hms and gms remote message notification
 *
 * This class maps to the fields of a notification message.
 *
 * @see [com.google.firebase.messaging.RemoteMessage.Notification]
 * @see [com.huawei.hms.push.RemoteMessage.Notification]
 */
interface Notification {

    val defaultVibrateSettings: Boolean
    val notificationCount: Int?
    val eventTime: Long?
    val titleLocalizationKey: String?
    val localOnly: Boolean
    val link: Uri?
    val channelId: String?
    val visibility: Int?
    val clickAction: String?
    val notificationPriority: Int?
    val title: String?
    val tag: String?
    val sound: String?
    val defaultSound: Boolean
    val body: String?
    val imageUrl: Uri?
    val icon: String?
    val sticky: Boolean
    val color: String?
    val lightSettings: IntArray?
    val defaultLightSettings: Boolean
    val vibrateTimings: LongArray?
    val bodyLocalizationKey: String?
    val bodyLocalizationArgs: Array<String>?
    val ticker: String?
    val titleLocalizationArgs: Array<String>?

    companion object {

        /**
         * Create [Notification] object from [com.google.firebase.messaging.RemoteMessage.Notification]
         */
        internal fun com.google.firebase.messaging.RemoteMessage.Notification.toChoiceNotification(): Notification {
            return object :
                Notification {
                override val defaultVibrateSettings: Boolean
                    get() = this@toChoiceNotification.defaultVibrateSettings
                override val notificationCount: Int?
                    get() = this@toChoiceNotification.notificationCount
                override val eventTime: Long?
                    get() = this@toChoiceNotification.eventTime
                override val titleLocalizationKey: String?
                    get() = this@toChoiceNotification.titleLocalizationKey
                override val localOnly: Boolean
                    get() = this@toChoiceNotification.localOnly
                override val link: Uri?
                    get() = this@toChoiceNotification.link
                override val channelId: String?
                    get() = this@toChoiceNotification.channelId
                override val visibility: Int?
                    get() = this@toChoiceNotification.visibility
                override val clickAction: String?
                    get() = this@toChoiceNotification.clickAction
                override val notificationPriority: Int?
                    get() = this@toChoiceNotification.notificationPriority
                override val title: String?
                    get() = this@toChoiceNotification.title
                override val tag: String?
                    get() = this@toChoiceNotification.tag
                override val sound: String?
                    get() = this@toChoiceNotification.sound
                override val defaultSound: Boolean
                    get() = this@toChoiceNotification.defaultSound
                override val body: String?
                    get() = this@toChoiceNotification.body
                override val imageUrl: Uri?
                    get() = this@toChoiceNotification.imageUrl
                override val icon: String?
                    get() = this@toChoiceNotification.icon
                override val sticky: Boolean
                    get() = this@toChoiceNotification.sticky
                override val color: String?
                    get() = this@toChoiceNotification.color
                override val lightSettings: IntArray?
                    get() = this@toChoiceNotification.lightSettings
                override val defaultLightSettings: Boolean
                    get() = this@toChoiceNotification.defaultLightSettings
                override val vibrateTimings: LongArray?
                    get() = this@toChoiceNotification.vibrateTimings
                override val bodyLocalizationKey: String?
                    get() = this@toChoiceNotification.bodyLocalizationKey
                override val bodyLocalizationArgs: Array<String>?
                    get() = this@toChoiceNotification.bodyLocalizationArgs
                override val ticker: String?
                    get() = this@toChoiceNotification.ticker
                override val titleLocalizationArgs: Array<String>?
                    get() = this@toChoiceNotification.titleLocalizationArgs

            }
        }

        /**
         * Create [Notification] object from [com.huawei.hms.push.RemoteMessage.Notification]
         */
        internal fun com.huawei.hms.push.RemoteMessage.Notification.toChoiceNotification(): Notification {
            return object :
                Notification {
                override val defaultVibrateSettings: Boolean
                    get() = this@toChoiceNotification.isDefaultVibrate
                override val notificationCount: Int?
                    get() = this@toChoiceNotification.badgeNumber
                override val eventTime: Long?
                    get() = this@toChoiceNotification.`when`
                override val titleLocalizationKey: String?
                    get() = this@toChoiceNotification.titleLocalizationKey
                override val localOnly: Boolean
                    get() = this@toChoiceNotification.isLocalOnly
                override val link: Uri?
                    get() = this@toChoiceNotification.link
                override val channelId: String?
                    get() = this@toChoiceNotification.channelId
                override val visibility: Int?
                    get() = this@toChoiceNotification.visibility
                override val clickAction: String?
                    get() = this@toChoiceNotification.clickAction
                override val notificationPriority: Int?
                    get() = this@toChoiceNotification.importance
                override val title: String?
                    get() = this@toChoiceNotification.title
                override val tag: String?
                    get() = this@toChoiceNotification.tag
                override val sound: String?
                    get() = this@toChoiceNotification.sound
                override val defaultSound: Boolean
                    get() = this@toChoiceNotification.isDefaultSound
                override val body: String?
                    get() = this@toChoiceNotification.body
                override val imageUrl: Uri?
                    get() = this@toChoiceNotification.imageUrl
                override val icon: String?
                    get() = this@toChoiceNotification.icon
                override val sticky: Boolean
                    get() = this@toChoiceNotification.isAutoCancel
                override val color: String?
                    get() = this@toChoiceNotification.color
                override val lightSettings: IntArray?
                    get() = this@toChoiceNotification.lightSettings
                override val defaultLightSettings: Boolean
                    get() = this@toChoiceNotification.isDefaultLight
                override val vibrateTimings: LongArray?
                    get() = this@toChoiceNotification.vibrateConfig
                override val bodyLocalizationKey: String?
                    get() = this@toChoiceNotification.bodyLocalizationKey
                override val bodyLocalizationArgs: Array<String>?
                    get() = this@toChoiceNotification.bodyLocalizationArgs
                override val ticker: String?
                    get() = this@toChoiceNotification.ticker
                override val titleLocalizationArgs: Array<String>?
                    get() = this@toChoiceNotification.titleLocalizationArgs
            }
        }
    }
}