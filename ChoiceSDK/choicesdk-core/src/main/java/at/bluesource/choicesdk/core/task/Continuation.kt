package at.bluesource.choicesdk.core.task

/**
 *
 *
 * @param TResult type of Task
 * @param TContinuationResult type of the continuation Result
 */
interface Continuation<TResult, TContinuationResult> {
    @Throws(Exception::class)
    fun then(result: Task<TResult?>): TContinuationResult?
}
