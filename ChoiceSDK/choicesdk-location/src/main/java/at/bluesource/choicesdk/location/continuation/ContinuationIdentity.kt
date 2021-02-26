package at.bluesource.choicesdk.location.continuation


/**
 * Special case of [at.bluesource.choicesdk.core.task.Continuation]
 * result is unchanged if no exception was found otherwise throws exception
 */
internal class ContinuationIdentity<TResult> : ContinuationWithConversion<TResult?, TResult?>() {
    override fun convertResult(result: TResult?): TResult? {
        return result
    }
}