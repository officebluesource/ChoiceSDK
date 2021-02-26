package at.bluesource.choicesdk.core.task.listener

import at.bluesource.choicesdk.core.task.Task

/**
 * Interface for completed task listeners
 *
 * @param TResult type of the task
 */
interface OnCompleteListener<TResult> {
    fun onComplete(result: Task<TResult>)
}
