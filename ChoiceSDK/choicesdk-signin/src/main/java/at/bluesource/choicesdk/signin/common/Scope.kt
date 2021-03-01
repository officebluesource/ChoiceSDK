package at.bluesource.choicesdk.signin.common

/**
 * Scopes for [SignInService]; see [Scopes] for common strings
 *
 * Describes the authorization request for OAuth 2.0.
 * This has security implications for the user, and requesting additional scopes will result in authorization dialogs.
 *
 * @see com.google.android.gms.common.api.Scope
 * @see com.huawei.hms.support.api.entity.auth.Scope
 */
data class Scope(var scopeUri: String) {
    companion object {
        internal fun Scope.toGmsScope(): com.google.android.gms.common.api.Scope {
            return com.google.android.gms.common.api.Scope(scopeUri)
        }

        internal fun Scope.toHmsScope(): com.huawei.hms.support.api.entity.auth.Scope {
            return com.huawei.hms.support.api.entity.auth.Scope(scopeUri)
        }

        internal fun com.google.android.gms.common.api.Scope.toChoiceScope(): Scope {
            return Scope(this@toChoiceScope.toString())
        }

        internal fun com.huawei.hms.support.api.entity.auth.Scope.toChoiceScope(): Scope {
            return Scope(this@toChoiceScope.toString())
        }
    }
}