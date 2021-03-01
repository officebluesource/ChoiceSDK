package at.bluesource.choicesdk.location.continuation

import at.bluesource.choicesdk.core.exception.ExceptionConverter
import at.bluesource.choicesdk.core.task.Continuation
import at.bluesource.choicesdk.core.task.Task


/**
 * Special case of [at.bluesource.choicesdk.core.task.Continuation]
 * converts result if no exception was found otherwise throws exception
 */
internal abstract class ContinuationWithConversion<TResult, TContinuationResult> :
    Continuation<TResult?, TContinuationResult?> {

    override fun then(result: Task<TResult?>): TContinuationResult? {
        val exception: Exception? = result.getException()
        if (exception != null) {
            convertException(exception)?.let { throw it }
        }
        return result.getResult()?.let { convertResult(it) }
    }

    abstract fun convertResult(result: TResult?): TContinuationResult?

    private fun convertException(original: Exception?): Throwable? {
        return ExceptionConverter.convertException(original)
    }
}