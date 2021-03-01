package at.bluesource.choicesdk.messaging.gms

import android.content.Context
import android.util.Log
import at.bluesource.choicesdk.messaging.common.TokenProvider
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

internal object GmsTokenProvider : TokenProvider {
    private const val LOG_TAG = "GmsTokenProvider"

    override suspend fun requestToken(context: Context): String {
        val result: String? = try {
            FirebaseMessaging.getInstance().token.await()
        } catch (e: Exception) {
            Log.d(LOG_TAG, "get token not successful")
            null
        }
        return result ?: ""
    }

    override suspend fun deleteToken(context: Context) {
        try {
            FirebaseMessaging.getInstance().deleteToken().await()
        } catch (e: Exception) {
            Log.d(LOG_TAG, "delete token not successful")
        }
    }

}