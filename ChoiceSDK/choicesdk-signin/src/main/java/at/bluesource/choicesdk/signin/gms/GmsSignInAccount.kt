package at.bluesource.choicesdk.signin.gms

import android.accounts.Account
import android.net.Uri
import at.bluesource.choicesdk.signin.common.Scope
import at.bluesource.choicesdk.signin.common.Scope.Companion.toChoiceScope
import at.bluesource.choicesdk.signin.common.SignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

/**
 * Wrapper class for google account
 *
 * @property gAccount google SignInAccount instance
 * @see com.google.android.gms.auth.api.signin.GoogleSignInAccount
 */
internal class GmsSignInAccount(private val gAccount: GoogleSignInAccount) : SignInAccount {
    override val account: Account?
        get() = gAccount.account

    override val displayName: String?
        get() = gAccount.displayName
    override val email: String?
        get() = gAccount.email
    override val familyName: String?
        get() = gAccount.familyName
    override val givenName: String?
        get() = gAccount.givenName
    override val gender: Int
        get() = SignInAccount.GENDER_UNKNOWN
    override val authorizedScopes: Set<Scope>
        get() = gAccount.grantedScopes.map { it.toChoiceScope() }.toSet()
    override val idToken: String?
        get() = gAccount.idToken
    override val authToken: String?
        get() = gAccount.serverAuthCode
    override val photoUrl: Uri?
        get() = gAccount.photoUrl
}