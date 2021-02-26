# Sign in

GMS and HMS sign-in is pretty simple as well. The only difference between them is that GMS returns an account on the SDK call itself (only if previously logged in) and HMS always returns the account in `onActivityResult`. The preferred way is to get an `Observable<SignInAccount>` and observe changes that way.

## Usage

Observable way:
```kotlin
val signIn: SignInService = SignInService.getInstance()

val observer: DisposableObserver<SignInAccount> = object : DisposableObserver<SignInAccount>() {
    override fun onNext(account: SignInAccount) {}
    override fun onError(e: Throwable?) {}
    override fun onComplete() {}
}

signIn.getAccountObservable().subscribeWith(observer)
signIn.signIn(this)
```

Standard way:
```kotlin
val signIn: SignInService = SignInService.getInstance()
val account: SignInAccount? = signIn.signIn(this) // only valid if previously logged in with GMS

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    try {
        val account: SignInAccount? = signIn.onActivityResult(requestCode, resultCode, data)
    } catch (e: Exception) {
        Log.d(TAG, e.toString())
    }
}
```

## Links
- [GMS Signin](https://developers.google.com/identity/sign-in/android/start-integrating)
- [HMS Signin](https://developer.huawei.com/consumer/en/hms/huawei-accountkit)