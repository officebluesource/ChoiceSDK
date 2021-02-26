@file:Suppress("unused")

package at.bluesource.choicesdk.signin.common

import android.accounts.Account
import android.net.Uri
import at.bluesource.choicesdk.signin.gms.GmsSignInAccount
import at.bluesource.choicesdk.signin.hms.HmsSignInAccount

/**
 * Common interface for gms and hms account
 *
 * Class that holds the basic account information of the signed in user.
 *
 * @see com.google.android.gms.auth.api.signin.GoogleSignInAccount
 * @see com.huawei.hms.support.hwid.result.AuthHuaweiId
 */
interface SignInAccount {
    val account: Account?
    val displayName: String?
    val email: String?
    val familyName: String?
    val givenName: String?
    val gender: Int
    val authorizedScopes: Set<Scope>
    val idToken: String?
    val authToken: String?
    val photoUrl: Uri?

    companion object {
        const val GENDER_UNKNOWN = -1
        const val GENDER_MALE = -1
        const val GENDER_FEMALE = -1
        const val GENDER_SECRET = -1

        internal fun com.google.android.gms.auth.api.signin.GoogleSignInAccount.toChoiceSignInAccount(): SignInAccount {
            return GmsSignInAccount(this@toChoiceSignInAccount)
        }

        internal fun com.huawei.hms.support.hwid.result.AuthHuaweiId.toChoiceSignInAccount(): SignInAccount {
            return HmsSignInAccount(this@toChoiceSignInAccount)
        }
    }
}