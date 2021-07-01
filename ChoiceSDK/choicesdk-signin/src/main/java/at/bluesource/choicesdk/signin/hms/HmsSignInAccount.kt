package at.bluesource.choicesdk.signin.hms

import android.accounts.Account
import android.net.Uri
import at.bluesource.choicesdk.core.ChoiceSdk
import at.bluesource.choicesdk.signin.common.Scope
import at.bluesource.choicesdk.signin.common.Scope.Companion.toChoiceScope
import at.bluesource.choicesdk.signin.common.SignInAccount
import com.huawei.hms.support.hwid.result.AuthHuaweiId

/**
 * Wrapper class for hms account
 *
 * @property hmsAccount AuthHuaweiId account instance
 * @see com.huawei.hms.support.hwid.result.AuthHuaweiId
 */
internal class HmsSignInAccount(private val hmsAccount: AuthHuaweiId) : SignInAccount {
    override val account: Account?
        get() = hmsAccount.getHuaweiAccount(ChoiceSdk.getContext())
    override val displayName: String?
        get() = hmsAccount.displayName
    override val email: String?
        get() = hmsAccount.email
    override val familyName: String?
        get() = hmsAccount.familyName
    override val givenName: String?
        get() = hmsAccount.givenName
    override val gender: Int
        get() = hmsAccount.gender
    override val authorizedScopes: Set<Scope>
        get() = hmsAccount.authorizedScopes.map { it.toChoiceScope() }.toSet()
    override val idToken: String?
        get() = hmsAccount.idToken
    override val authToken: String?
        get() = hmsAccount.authorizationCode
    override val photoUrl: Uri?
        get() = hmsAccount.avatarUri

}