package at.bluesource.choicesdk.messaging.factory

import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.messaging.common.MessagingRepository
import at.bluesource.choicesdk.messaging.common.MessagingService
import at.bluesource.choicesdk.messaging.common.TokenProvider
import at.bluesource.choicesdk.messaging.gms.GmsTokenProvider
import at.bluesource.choicesdk.messaging.hms.HmsTokenProvider

/**
 * MessagingRepository factory, uses [MobileServicesDetector] to get instance of [MessagingService] and [TokenProvider] (repository)
 * Automatically decides if GMS or HMS should be used. GMS is always preferred first.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see MessagingRepository
 */
class MessagingRepositoryFactory {
    companion object {

        @Throws(UnsupportedOperationException::class)
        fun getMessagingService(): MessagingService {
            return MessagingRepository.getInstance()
        }

        @Throws(UnsupportedOperationException::class)
        fun getTokenProvider(): TokenProvider {
            return when {
                MobileServicesDetector.isGmsAvailable() -> {
                    GmsTokenProvider
                }
                MobileServicesDetector.isHmsAvailable() -> {
                    HmsTokenProvider
                }
                else -> {
                    throw UnsupportedOperationException("Missing underlying GMS/HMS API.")
                }
            }

        }
    }
}