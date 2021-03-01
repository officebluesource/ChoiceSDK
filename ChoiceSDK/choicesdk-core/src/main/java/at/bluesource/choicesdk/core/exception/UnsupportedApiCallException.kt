package at.bluesource.choicesdk.core.exception


/**
 * Wrapper class for hms and gms UnsupportedApiCallException
 *
 * Note: hms version is deprecated
 *
 * @see com.google.android.gms.common.api.UnsupportedApiCallException
 * @see com.huawei.hms.common.api.UnsupportedApiCallException
 */
abstract class UnsupportedApiCallException internal constructor() : ApiException() {

    companion object {

        fun createFrom(exception: Exception): UnsupportedApiCallException {
            return when (exception) {
                is com.google.android.gms.common.api.UnsupportedApiCallException -> fromGmsException(
                    exception
                )
                is com.huawei.hms.common.api.UnsupportedApiCallException -> fromHmsException(
                    exception
                )
                else -> throw IllegalArgumentException()
            }
        }

        private fun fromHmsException(exception: com.huawei.hms.common.api.UnsupportedApiCallException): UnsupportedApiCallException {
            return object : UnsupportedApiCallException() {
                override fun getStatusCode(): Int {
                    return -1
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

        private fun fromGmsException(exception: com.google.android.gms.common.api.UnsupportedApiCallException): UnsupportedApiCallException {
            return object : UnsupportedApiCallException() {
                override fun getStatusCode(): Int {
                    return -1
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