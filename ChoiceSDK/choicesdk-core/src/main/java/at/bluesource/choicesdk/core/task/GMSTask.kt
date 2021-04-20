package at.bluesource.choicesdk.core.task

import android.app.Activity
import at.bluesource.choicesdk.core.task.listener.OnCanceledListener
import at.bluesource.choicesdk.core.task.listener.OnCompleteListener
import at.bluesource.choicesdk.core.task.listener.OnFailureListener
import at.bluesource.choicesdk.core.task.listener.OnSuccessListener
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Wrapper class for gms version of task
 *
 * @param TResult type of the Task
 * @property task gms Task to wrap
 * @see com.google.android.gms.tasks.Task
 */
class GmsTask<TResult>(private var task: com.google.android.gms.tasks.Task<TResult>) :
    Task<TResult> {
    override fun addOnCanceledListener(listener: OnCanceledListener): Task<TResult?> {
        return GmsTask<TResult?>(task.addOnCanceledListener { listener.onCanceled() })
    }

    override fun addOnCanceledListener(
        executor: Executor,
        listener: OnCanceledListener
    ): Task<TResult?> {
        return GmsTask<TResult?>(
            task.addOnCanceledListener(
                executor,
                com.google.android.gms.tasks.OnCanceledListener { listener.onCanceled() }
            )
        )
    }

    override fun addOnCanceledListener(
        activity: Activity,
        listener: OnCanceledListener
    ): Task<TResult?> {
        return GmsTask<TResult?>(
            task.addOnCanceledListener(
                activity
            ) { listener.onCanceled() }
        )
    }

    override fun addOnCompleteListener(listener: OnCompleteListener<TResult?>): Task<TResult?> {
        return GmsTask<TResult?>(
            task.addOnCompleteListener { task ->
                listener.onComplete(
                    GmsTask<TResult?>(
                        task
                    )
                )
            }
        )
    }

    override fun addOnCompleteListener(
        activity: Activity,
        listener: OnCompleteListener<TResult?>
    ): Task<TResult?> {
        return GmsTask<TResult?>(
            task.addOnCompleteListener(
                activity
            ) { task ->
                listener.onComplete(
                    GmsTask<TResult?>(task)
                )
            }
        )
    }

    override fun addOnCompleteListener(
        executor: Executor,
        listener: OnCompleteListener<TResult?>
    ): Task<TResult?> {
        return GmsTask<TResult?>(
            task.addOnCompleteListener(
                executor,
                com.google.android.gms.tasks.OnCompleteListener { task ->
                    listener.onComplete(
                        GmsTask<TResult?>(
                            task
                        )
                    )
                }
            )
        )
    }

    override fun addOnFailureListener(
        activity: Activity,
        listener: OnFailureListener
    ): Task<TResult?> {
        return GmsTask<TResult?>(
            task.addOnFailureListener(
                activity
            ) { e -> listener.onFailure(e) }
        )
    }

    override fun addOnFailureListener(listener: OnFailureListener): Task<TResult?> {
        return GmsTask<TResult?>(task.addOnFailureListener { e ->
            listener.onFailure(
                e
            )
        })
    }

    override fun addOnFailureListener(
        executor: Executor,
        listener: OnFailureListener
    ): Task<TResult?> {
        return GmsTask<TResult?>(
            task.addOnFailureListener(
                executor,
                com.google.android.gms.tasks.OnFailureListener { e -> listener.onFailure(e) })
        )
    }

    override fun addOnSuccessListener(
        executor: Executor,
        listener: OnSuccessListener<in TResult?>
    ): Task<TResult?> {
        return GmsTask<TResult?>(
            task.addOnSuccessListener(
                executor,
                com.google.android.gms.tasks.OnSuccessListener { result ->
                    listener.onSuccess(
                        result
                    )
                }
            )
        )
    }

    override fun addOnSuccessListener(listener: OnSuccessListener<in TResult?>): Task<TResult?> {
        return GmsTask<TResult?>(task.addOnSuccessListener { result ->
            listener.onSuccess(
                result
            )
        })
    }

    override fun addOnSuccessListener(
        activity: Activity,
        listener: OnSuccessListener<in TResult?>
    ): Task<TResult?> {
        return GmsTask<TResult?>(
            task.addOnSuccessListener(
                activity
            ) { result -> listener.onSuccess(result) }
        )
    }

    override fun <TContinuationResult> continueWith(continuation: Continuation<TResult?, TContinuationResult?>): Task<TContinuationResult?> {
        return GmsTask<TContinuationResult?>(
            task.continueWith { task ->
                continuation.then(
                    GmsTask<TResult?>(task)
                )
            }
        )
    }

    override fun <TContinuationResult> continueWith(
        executor: Executor,
        continuation: Continuation<TResult?, TContinuationResult?>
    ): Task<TContinuationResult?> {
        return GmsTask<TContinuationResult?>(
            task.continueWith<TContinuationResult?>(
                executor,
                com.google.android.gms.tasks.Continuation<TResult?, TContinuationResult?> { task ->
                    continuation.then(
                        GmsTask<TResult?>(
                            task
                        )
                    )
                }
            )
        )
    }

    override fun <TContinuationResult> continueWithTask(continuation: Continuation<TResult?, Task<TContinuationResult?>?>): Task<TContinuationResult?> {
        return GmsTask<TContinuationResult?>(
            task.continueWithTask<TContinuationResult?> { task ->
                (continuation.then(
                    GmsTask<TResult?>(task)
                ) as GmsTask<TContinuationResult?>).task
            }
        )
    }

    override fun <TContinuationResult> continueWithTask(
        executor: Executor,
        continuation: Continuation<TResult?, Task<TContinuationResult?>?>
    ): Task<TContinuationResult?> {
        return GmsTask<TContinuationResult?>(
            task.continueWithTask<TContinuationResult?>(
                executor,
                com.google.android.gms.tasks.Continuation<TResult?, com.google.android.gms.tasks.Task<TContinuationResult?>?> { task ->
                    (continuation.then(
                        GmsTask<TResult?>(
                            task
                        )
                    ) as GmsTask<TContinuationResult?>).task
                }
            )
        )
    }

    override fun getException(): Exception? {
        return task.exception
    }

    override fun getResult(): TResult? {
        return task.result
    }

    override fun <X : Throwable?> getResult(exceptionType: Class<X>): TResult? {
        return task.getResult(exceptionType)
    }

    override fun isCanceled(): Boolean {
        return task.isCanceled
    }

    override fun isComplete(): Boolean {
        return task.isComplete
    }

    override fun isSuccessful(): Boolean {
        return task.isSuccessful
    }

    override fun <TContinuationResult> onSuccessTask(
        executor: Executor,
        successContinuation: SuccessContinuation<TResult?, TContinuationResult?>
    ): Task<TContinuationResult?> {
        return GmsTask<TContinuationResult?>(
            task.onSuccessTask<TContinuationResult?>(
                executor,
                com.google.android.gms.tasks.SuccessContinuation<TResult, TContinuationResult?> {
                    ((successContinuation.then(it) as GmsTask<TContinuationResult?>?)!!).task
                }
            )
        )
    }

    override fun <TContinuationResult> onSuccessTask(successContinuation: SuccessContinuation<TResult?, TContinuationResult?>): Task<TContinuationResult?> {
        return GmsTask<TContinuationResult?>(
            task.onSuccessTask {
                ((successContinuation.then(it) as GmsTask<TContinuationResult?>?)!!).task
            }
        )
    }

    override suspend fun await(): TResult? = suspendCoroutine { continuation ->
        val listener = object : OnCompleteListener<TResult?> {
            override fun onComplete(task: Task<TResult?>) {
                if (task.isSuccessful()) {
                    continuation.resume(task.getResult())
                } else {
                    continuation.resumeWithException(task.getException() ?: RuntimeException("Unknown task exception"))
                }
            }
        }
        addOnCompleteListener(listener)
    }

    companion object {
        fun <TResult> com.google.android.gms.tasks.Task<TResult>.toGMSTask(): GmsTask<TResult> {
            return GmsTask<TResult>(this)
        }
    }
}