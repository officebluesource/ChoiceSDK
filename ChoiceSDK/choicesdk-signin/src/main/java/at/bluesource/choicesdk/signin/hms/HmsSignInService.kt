package at.bluesource.choicesdk.signin.hms

import android.app.Activity
import android.content.Intent
import at.bluesource.choicesdk.core.exception.ExceptionConverter.Companion.convertException
import at.bluesource.choicesdk.core.task.HmsTask
import at.bluesource.choicesdk.core.task.listener.OnCompleteListener
import at.bluesource.choicesdk.signin.common.Scope
import at.bluesource.choicesdk.signin.common.Scope.Companion.toHmsScope
import at.bluesource.choicesdk.signin.common.SignInAccount
import at.bluesource.choicesdk.signin.common.SignInAccount.Companion.toChoiceSignInAccount
import at.bluesource.choicesdk.signin.common.SignInService
import com.huawei.hmf.tasks.Task
import com.huawei.hms.common.ApiException
import com.huawei.hms.support.hwid.HuaweiIdAuthManager
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper
import com.huawei.hms.support.hwid.result.AuthHuaweiId
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable

/**
 * Wrapper class for huawei sign in service
 *
 * @see com.huawei.hms.support.hwid.HuaweiIdAuthManager
 */
internal class HmsSignInService private constructor() : SignInService {
    private val accountRelay: BehaviorRelay<SignInAccount> = BehaviorRelay.create()

    override fun getAccountObservable(): Observable<SignInAccount> {
        return accountRelay
    }

    override fun signIn(activity: Activity, vararg scopes: Scope?): SignInAccount? {
        val huaweiIdAuthParamsHelper =
            HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setIdToken()

        val list = scopes.map { it?.toHmsScope() }.toMutableList()
        if (list.size > 0) {
            huaweiIdAuthParamsHelper.setScopeList(list)
        }

        val huaweiIdAuthService =
            HuaweiIdAuthManager.getService(activity, huaweiIdAuthParamsHelper.createParams())
        huaweiIdAuthService?.let {
            activity.startActivityForResult(it.signInIntent, RC_SIGN_IN)
        }

        return null
    }

    override fun signIn(activity: Activity, scopes: List<Scope>): SignInAccount? {
        return this.signIn(activity, *scopes.toTypedArray())
    }

    override fun signOut(activity: Activity, onCompleteListener: OnCompleteListener<Void>) {
        val huaweiIdAuthService = HuaweiIdAuthManager.getService(
            activity,
            HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM).setIdToken()
                .createParams()
        )
        huaweiIdAuthService.signOut().addOnCompleteListener {
            onCompleteListener.onComplete(HmsTask(it))
        }
    }

    @Throws(at.bluesource.choicesdk.core.exception.ApiException::class)
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ): SignInAccount? {
        if (requestCode == RC_SIGN_IN) {
            //login success
            //get user message by parseAuthResultFromIntent
            val authHuaweiIdTask: Task<AuthHuaweiId> =
                HuaweiIdAuthManager.parseAuthResultFromIntent(data)
            if (authHuaweiIdTask.isSuccessful) {
                val choiceAccount = authHuaweiIdTask.result.toChoiceSignInAccount()
                accountRelay.accept(choiceAccount)
                return choiceAccount
            } else {
                val ce = convertException(authHuaweiIdTask.exception as ApiException)
                if (ce != null) throw ce
            }
        }
        return null
    }

    companion object {
        const val RC_SIGN_IN = 1111

        @Volatile
        private var INSTANCE: HmsSignInService? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        fun getInstance(): HmsSignInService {
            return INSTANCE
                ?: synchronized(this) {
                    HmsSignInService().also {
                        INSTANCE = it
                    }
                }
        }
    }
}