package at.bluesource.choicesdk.signin.gms

import android.app.Activity
import android.content.Intent
import at.bluesource.choicesdk.core.exception.ExceptionConverter
import at.bluesource.choicesdk.core.task.GmsTask
import at.bluesource.choicesdk.core.task.listener.OnCompleteListener
import at.bluesource.choicesdk.signin.common.Scope
import at.bluesource.choicesdk.signin.common.Scope.Companion.toGmsScope
import at.bluesource.choicesdk.signin.common.SignInAccount
import at.bluesource.choicesdk.signin.common.SignInAccount.Companion.toChoiceSignInAccount
import at.bluesource.choicesdk.signin.common.SignInService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable

/**
 * Wrapper class for google sign in service
 *
 * @see com.google.android.gms.auth.api.signin.GoogleSignIn
 */
internal class GmsSignInService private constructor() : SignInService {
    private val accountRelay: BehaviorRelay<SignInAccount> = BehaviorRelay.create()

    override fun getAccountObservable(): Observable<SignInAccount> {
        return accountRelay
    }

    override fun signIn(activity: Activity, vararg scopes: Scope?): SignInAccount? {
        val gsob = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()

        val list = scopes.map { it?.toGmsScope() }.toMutableList()

        if (list.size > 1) {
            val first = list.first()
            list.removeAt(0)
            if (first != null) {
                gsob.requestScopes(first, *list.toTypedArray())
            }

        } else if (list.size != 0) {
            list.first()?.let { gsob.requestScopes(it) }
        }

        val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(activity, gsob.build())
        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(activity)

        if (account != null) {
            val choiceAccount = account.toChoiceSignInAccount()
            accountRelay.accept(choiceAccount)
            return choiceAccount
        }

        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, RC_SIGN_IN)

        return null
    }

    override fun signIn(activity: Activity, scopes: List<Scope>): SignInAccount? {
        return this.signIn(activity, *scopes.toTypedArray())
    }

    override fun signOut(activity: Activity, onCompleteListener: OnCompleteListener<Void>) {
        val googleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(
            activity,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        )

        googleSignInClient.signOut().addOnCompleteListener {
            onCompleteListener.onComplete(GmsTask(it))
        }
    }


    @Throws(at.bluesource.choicesdk.core.exception.ApiException::class)
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ): SignInAccount? {

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                return when (val account: GoogleSignInAccount? =
                    task.getResult(ApiException::class.java)) {
                    null -> null
                    else -> {
                        val choiceAccount = account.toChoiceSignInAccount()
                        accountRelay.accept(choiceAccount)
                        account.toChoiceSignInAccount()
                    }
                }
            } catch (e: ApiException) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                val ce = ExceptionConverter.convertException(e)
                if (ce != null) throw ce
            }
        }
        return null
    }

    companion object {
        const val RC_SIGN_IN = 1111

        @Volatile
        private var INSTANCE: GmsSignInService? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        fun getInstance(): GmsSignInService {
            return INSTANCE
                ?: synchronized(this) {
                    GmsSignInService().also {
                        INSTANCE = it
                    }
                }
        }
    }
}