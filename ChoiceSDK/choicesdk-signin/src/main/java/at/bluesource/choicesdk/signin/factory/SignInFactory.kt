package at.bluesource.choicesdk.signin.factory

import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.signin.common.SignInService
import at.bluesource.choicesdk.signin.gms.GmsSignInService
import at.bluesource.choicesdk.signin.hms.HmsSignInService

/**
 * SignIn factory, uses [MobileServicesDetector] to get instance of [SignInService]
 * Automatically decides if GMS or HMS should be used. GMS is always preferred first.
 *
 * Throws [UnsupportedOperationException] if no underlying api has been found.
 *
 * @see GmsSignInService
 * @see HmsSignInService
 */
internal class SignInFactory {
    companion object {
        @Throws(UnsupportedOperationException::class)
        fun getSignIn(): SignInService {
            return when (MobileServicesDetector.getAvailableMobileService()) {
                MobileService.GMS -> GmsSignInService.getInstance()
                MobileService.HMS -> HmsSignInService.getInstance()
            }
        }
    }
}