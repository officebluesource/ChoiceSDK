package at.bluesource.choicesdk.core

/**
 * Wrapper class for observables
 *
 * Success class contains result if successful
 *
 * Failure class contains exception and extra message if exception was thrown
 *
 * Casting to superclass works since out parameter is used, see: https://kotlinlang.org/docs/reference/generics.html#variance
 */
sealed class Outcome<out T : Any> {
    data class Success<out T : Any>(val value: T) : Outcome<T>()
    data class Failure(val error: Throwable, val extraMessage: String? = null) : Outcome<Nothing>()
}
