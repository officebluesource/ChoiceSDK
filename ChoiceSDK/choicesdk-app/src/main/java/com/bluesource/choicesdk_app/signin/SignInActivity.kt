package com.bluesource.choicesdk_app.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import at.bluesource.choicesdk.signin.common.Scope
import at.bluesource.choicesdk.signin.common.Scopes
import at.bluesource.choicesdk.signin.common.SignInAccount
import at.bluesource.choicesdk.signin.common.SignInService
import com.bluesource.choicesdk_app.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver

class SignInActivity : AppCompatActivity() {
    private val TAG = "Test"
    private lateinit var signIn: SignInService
    private lateinit var textView: TextView
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInBtn: Button = findViewById(R.id.btn_sign_in)
        textView = findViewById(R.id.textView_account)
        signIn = SignInService.getInstance()

        val observer: DisposableObserver<SignInAccount> =
            object : DisposableObserver<SignInAccount>() {
                override fun onComplete() {}

                override fun onNext(account: SignInAccount) {
                    val msg =
                        "${account.displayName}; ${account.email}; ${account.account.toString()}; ${account.authToken}; ${account.photoUrl}"
                    textView.text = msg
                    Log.d(TAG, msg)
                    Toast.makeText(this@SignInActivity, "Signed in!", Toast.LENGTH_LONG).show()
                }

                override fun onError(e: Throwable?) {}
            }
        disposables.add(observer)
        signIn.getAccountObservable().observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)

        signInBtn.setOnClickListener {
            signIn.signIn(this, listOf(Scope(Scopes.EMAIL), Scope(Scopes.PROFILE)))
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            signIn.onActivityResult(requestCode, resultCode, data)

        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}