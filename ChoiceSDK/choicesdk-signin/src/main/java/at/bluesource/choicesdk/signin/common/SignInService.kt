package at.bluesource.choicesdk.signin.common

import android.app.Activity
import android.content.Intent
import at.bluesource.choicesdk.core.exception.ApiException
import at.bluesource.choicesdk.core.task.listener.OnCompleteListener
import at.bluesource.choicesdk.signin.factory.SignInFactory
import io.reactivex.rxjava3.core.Observable

/**
 * Common interface for sign in
 *
 * A client for interacting with the gms/hms Sign In API.
 * Use [at.bluesource.choicesdk.ChoiceSdk.getSignInService] to obtain an instance of the [SignInService].
 *
 * @see com.google.android.gms.auth.api.signin.GoogleSignIn
 * @see com.huawei.hms.support.hwid.HuaweiIdAuthManager
 */
interface SignInService {
    fun getAccountObservable(): Observable<SignInAccount>
    fun signIn(activity: Activity, vararg scopes: Scope?): SignInAccount?
    fun signIn(activity: Activity, scopes: List<Scope>): SignInAccount?
    fun signOut(activity: Activity, onCompleteListener: OnCompleteListener<Void>)

    @Throws(ApiException::class)
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): SignInAccount?

    companion object {
        @Throws(UnsupportedOperationException::class)
        fun getInstance(): SignInService {
            return SignInFactory.getSignIn()
        }
    }
}