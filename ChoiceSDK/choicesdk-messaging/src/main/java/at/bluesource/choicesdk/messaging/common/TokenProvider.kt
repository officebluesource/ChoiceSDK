package at.bluesource.choicesdk.messaging.common

import android.content.Context

/**
 * Common interface for hms and gms token provider
 *
 *  TokenProvider singleton that provides methods for request a token from either hms or gms.
 *
 *
 * @see [at.bluesource.choicesdk.messaging.gms.GmsTokenProvider]
 * @see [at.bluesource.choicesdk.messaging.hms.HmsTokenProvider]]
 */
interface TokenProvider {
    suspend fun requestToken(context: Context): String
    suspend fun deleteToken(context: Context)
}