package at.bluesource.choicesdk.core.exception


/**
 * Wrapper class for hms and gms ApiException
 *
 * @see [com.google.android.gms.common.api.ApiException]
 * @see [com.huawei.hms.common.ApiException]
 */
abstract class ApiException internal constructor() : Exception() {

    abstract fun getStatusCode(): Int
    abstract override fun getLocalizedMessage(): String?
    abstract override fun getStackTrace(): Array<StackTraceElement>
    abstract override val message: String?
    abstract override val cause: Throwable?

    companion object {

        fun createFrom(exception: Exception): ApiException {
            return when (exception) {
                is com.google.android.gms.common.api.ApiException -> fromGmsException(exception)
                is com.huawei.hms.common.ApiException -> fromHmsException(exception)
                else -> throw IllegalArgumentException()
            }
        }

        private fun fromGmsException(exception: com.google.android.gms.common.api.ApiException): ApiException {
            return object : ApiException() {
                override fun getStatusCode(): Int {
                    return exception.statusCode
                }

                override fun getLocalizedMessage(): String? {
                    return exception.localizedMessage
                }

                override fun getStackTrace(): Array<StackTraceElement> {
                    return exception.stackTrace
                }

                override val message: String?
                    get() = exception.message

                override val cause: Throwable?
                    get() = exception.cause
            }
        }

        private fun fromHmsException(exception: com.huawei.hms.common.ApiException): ApiException {
            return object : ApiException() {
                override fun getStatusCode(): Int {
                    return exception.statusCode
                }

                override fun getLocalizedMessage(): String? {
                    return exception.localizedMessage
                }

                override fun getStackTrace(): Array<StackTraceElement> {
                    return exception.stackTrace
                }

                override val message: String?
                    get() = exception.message

                override val cause: Throwable?
                    get() = exception.cause
            }
        }
    }
}