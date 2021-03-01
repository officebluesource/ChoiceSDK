package at.bluesource.choicesdk.core.task

/**
 *
 *
 * @param TResult type of Task
 * @param TContinuationResult type of the ContinuationResult
 */
interface SuccessContinuation<TResult, TContinuationResult> {
    @Throws(Exception::class)
    fun then(result: TResult): Task<TContinuationResult>
}
