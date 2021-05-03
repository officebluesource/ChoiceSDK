package at.bluesource.choicesdk.core.task.listener

/**
 * Interface for successful tasks
 *
 * @param TResult type of task
 */
fun interface OnSuccessListener<TResult> {
    fun onSuccess(result: TResult)
}
