package at.bluesource.choicesdk.core.task

import android.app.Activity
import at.bluesource.choicesdk.core.task.listener.OnCanceledListener
import at.bluesource.choicesdk.core.task.listener.OnCompleteListener
import at.bluesource.choicesdk.core.task.listener.OnFailureListener
import at.bluesource.choicesdk.core.task.listener.OnSuccessListener
import java.util.concurrent.Executor

/**
 * Common interface for gms and hms Task
 */
interface Task<TResult> {

    fun addOnCanceledListener(listener: OnCanceledListener): Task<TResult?>

    fun addOnCanceledListener(
            executor: Executor,
            listener: OnCanceledListener
    ): Task<TResult?>

    fun addOnCanceledListener(
            activity: Activity,
            listener: OnCanceledListener
    ): Task<TResult?>

    fun addOnCompleteListener(listener: OnCompleteListener<TResult?>): Task<TResult?>

    fun addOnCompleteListener(
            activity: Activity,
            listener: OnCompleteListener<TResult?>
    ): Task<TResult?>

    fun addOnCompleteListener(
            executor: Executor,
            listener: OnCompleteListener<TResult?>
    ): Task<TResult?>

    fun addOnFailureListener(
            activity: Activity,
            listener: OnFailureListener
    ): Task<TResult?>

    fun addOnFailureListener(listener: OnFailureListener): Task<TResult?>

    fun addOnFailureListener(
            executor: Executor,
            listener: OnFailureListener
    ): Task<TResult?>

    fun addOnSuccessListener(
            executor: Executor,
            listener: OnSuccessListener<in TResult?>
    ): Task<TResult?>

    fun addOnSuccessListener(listener: OnSuccessListener<in TResult?>): Task<TResult?>

    fun addOnSuccessListener(
            activity: Activity,
            listener: OnSuccessListener<in TResult?>
    ): Task<TResult?>

    fun <TContinuationResult> continueWith(
            continuation: Continuation<TResult?, TContinuationResult?>
    ): Task<TContinuationResult?>

    fun <TContinuationResult> continueWith(
            executor: Executor,
            continuation: Continuation<TResult?, TContinuationResult?>
    ): Task<TContinuationResult?>

    fun <TContinuationResult> continueWithTask(
            continuation: Continuation<TResult?, Task<TContinuationResult?>?>
    ): Task<TContinuationResult?>

    fun <TContinuationResult> continueWithTask(
            executor: Executor,
            continuation: Continuation<TResult?, Task<TContinuationResult?>?>
    ): Task<TContinuationResult?>

    fun getException(): Exception?

    fun getResult(): TResult?

    fun <X : Throwable?> getResult(exceptionType: Class<X>): TResult?

    fun isCanceled(): Boolean

    fun isComplete(): Boolean

    fun isSuccessful(): Boolean

    fun <TContinuationResult> onSuccessTask(
            executor: Executor,
            successContinuation: SuccessContinuation<TResult?, TContinuationResult?>
    ): Task<TContinuationResult?>

    fun <TContinuationResult> onSuccessTask(
            successContinuation: SuccessContinuation<TResult?, TContinuationResult?>
    ): Task<TContinuationResult?>

    suspend fun await(): TResult?
}