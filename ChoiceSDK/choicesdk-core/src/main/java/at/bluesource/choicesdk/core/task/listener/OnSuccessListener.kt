package at.bluesource.choicesdk.core.task.listener

/**
 * Interface for successful tasks
 *
 * @param TResult type of task
 */
interface OnSuccessListener<TResult> {
    fun onSuccess(result: TResult)
}
