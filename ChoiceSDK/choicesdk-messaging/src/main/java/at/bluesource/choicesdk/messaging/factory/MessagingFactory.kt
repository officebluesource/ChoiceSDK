package at.bluesource.choicesdk.messaging.factory

import at.bluesource.choicesdk.core.ChoiceSdk
import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.messaging.common.Messaging
import at.bluesource.choicesdk.messaging.gms.GmsMessaging
import at.bluesource.choicesdk.messaging.hms.HmsMessaging

/**
 * Messaging factory, uses [MobileServicesDetector] to get instance of [Messaging]
 * Automatically decides if GMS or HMS should be used. GMS is always preferred first.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsMessaging
 * @see HmsMessaging
 */
class MessagingFactory {
    companion object {
        @Throws(UnsupportedOperationException::class)
        fun getMessaging(): Messaging {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsMessaging.getInstance()
                MobileService.HMS -> HmsMessaging.getInstance(ChoiceSdk.getContext())
            }
        }
    }
}