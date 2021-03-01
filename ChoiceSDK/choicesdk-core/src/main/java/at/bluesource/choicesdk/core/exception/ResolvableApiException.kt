package at.bluesource.choicesdk.core.exception

import android.app.Activity
import android.app.PendingIntent
import android.content.IntentSender.SendIntentException

/**
 * Wrapper class for hms and gms ResolvableApiException
 *
 * @see com.google.android.gms.common.api.ResolvableApiException
 * @see com.huawei.hms.common.ResolvableApiException
 */
abstract class ResolvableApiException internal constructor() : ApiException() {

    @Throws(SendIntentException::class)
    abstract fun startResolutionForResult(activity: Activity, requestCode: Int)
    abstract val resolution: PendingIntent

    companion object {

        fun createFrom(exception: Exception): ResolvableApiException {
            return when (exception) {
                is com.google.android.gms.common.api.ResolvableApiException -> fromGmsException(
                    exception
                )
                is com.huawei.hms.common.ResolvableApiException -> fromHmsException(exception)
                else -> throw IllegalArgumentException()
            }
        }

        private fun fromGmsException(exception: com.google.android.gms.common.api.ResolvableApiException): ResolvableApiException {
            return object : ResolvableApiException() {
                override fun startResolutionForResult(activity: Activity, requestCode: Int) {
                    exception.startResolutionForResult(activity, requestCode)
                }

                override val resolution: PendingIntent
                    get() = exception.resolution

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

        private fun fromHmsException(exception: com.huawei.hms.common.ResolvableApiException): ResolvableApiException {
            return object : ResolvableApiException() {
                override fun startResolutionForResult(activity: Activity, requestCode: Int) {
                    exception.startResolutionForResult(activity, requestCode)
                }

                override val resolution: PendingIntent
                    get() = exception.resolution

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