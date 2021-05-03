package at.bluesource.choicesdk.core.task.listener

/**
 * Interface for failed task listeners
 */
fun interface OnFailureListener {
    fun onFailure(e: Exception)
}
