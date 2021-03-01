package at.bluesource.choicesdk.core.exception

/**
 * Converter class for hms and gms exceptions
 *
 * Returns null if original exception is null, or if no exception could be parsed -> the original exception
 */
class ExceptionConverter {
    companion object {
        fun convertException(originalException: Exception?): Exception? {
            when (originalException) {
                null -> {
                    return null
                }
                is com.google.android.gms.common.api.ResolvableApiException -> {
                    return ResolvableApiException.createFrom(originalException)
                }
                is com.huawei.hms.common.ResolvableApiException -> {
                    return ResolvableApiException.createFrom(originalException)
                }
                is com.google.android.gms.common.api.ApiException -> {
                    return ApiException.createFrom(originalException)
                }
                is com.huawei.hms.common.ApiException -> {
                    return ApiException.createFrom(originalException)
                }
                is com.google.android.gms.common.api.UnsupportedApiCallException -> {
                    return UnsupportedApiCallException.createFrom(originalException)
                }
                is com.huawei.hms.common.api.UnsupportedApiCallException -> {
                    return UnsupportedApiCallException.createFrom(originalException)
                }
                else -> return originalException
            }
        }
    }
}