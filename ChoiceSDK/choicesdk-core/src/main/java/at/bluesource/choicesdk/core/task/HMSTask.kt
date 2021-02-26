package at.bluesource.choicesdk.core.task

import android.app.Activity
import at.bluesource.choicesdk.core.task.listener.OnCanceledListener
import at.bluesource.choicesdk.core.task.listener.OnCompleteListener
import at.bluesource.choicesdk.core.task.listener.OnFailureListener
import at.bluesource.choicesdk.core.task.listener.OnSuccessListener
import java.util.concurrent.Executor

/**
 * Wrapper class for hms version of task
 *
 * @param TResult type of the Task
 * @property task task gms Task to wrap
 * @see com.huawei.hmf.tasks.Task
 */
class HmsTask<TResult>(private var task: com.huawei.hmf.tasks.Task<TResult>) :
    Task<TResult> {
    override fun addOnCanceledListener(listener: OnCanceledListener): Task<TResult?> {
        return HmsTask<TResult?>(task.addOnCanceledListener { listener.onCanceled() })
    }

    override fun addOnCanceledListener(
        executor: Executor,
        listener: OnCanceledListener
    ): Task<TResult?> {
        return HmsTask<TResult?>(
            task.addOnCanceledListener(
                executor,
                com.huawei.hmf.tasks.OnCanceledListener { listener.onCanceled() }
            )
        )
    }

    override fun addOnCanceledListener(
        activity: Activity,
        listener: OnCanceledListener
    ): Task<TResult?> {
        return HmsTask<TResult?>(
            task.addOnCanceledListener(
                activity
            ) { listener.onCanceled() }
        )
    }

    override fun addOnCompleteListener(listener: OnCompleteListener<TResult?>): Task<TResult?> {
        return HmsTask<TResult?>(
            task.addOnCompleteListener { task ->
                listener.onComplete(
                    HmsTask<TResult?>(
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
        return HmsTask<TResult?>(
            task.addOnCompleteListener(
                activity
            ) { task ->
                listener.onComplete(
                    HmsTask<TResult?>(task)
                )
            }
        )
    }

    override fun addOnCompleteListener(
        executor: Executor,
        listener: OnCompleteListener<TResult?>
    ): Task<TResult?> {
        return HmsTask<TResult?>(
            task.addOnCompleteListener(
                executor,
                com.huawei.hmf.tasks.OnCompleteListener { task ->
                    listener.onComplete(
                        HmsTask<TResult?>(
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
        return HmsTask<TResult?>(
            task.addOnFailureListener(
                activity
            ) { e -> listener.onFailure(e) }
        )
    }

    override fun addOnFailureListener(listener: OnFailureListener): Task<TResult?> {
        return HmsTask<TResult?>(task.addOnFailureListener { e ->
            listener.onFailure(
                e
            )
        })
    }

    override fun addOnFailureListener(
        executor: Executor,
        listener: OnFailureListener
    ): Task<TResult?> {
        return HmsTask<TResult?>(
            task.addOnFailureListener(
                executor,
                com.huawei.hmf.tasks.OnFailureListener { e -> listener.onFailure(e) }
            )
        )
    }

    override fun addOnSuccessListener(
        executor: Executor,
        listener: OnSuccessListener<in TResult?>
    ): Task<TResult?> {
        return HmsTask<TResult?>(
            task.addOnSuccessListener(
                executor,
                com.huawei.hmf.tasks.OnSuccessListener { result ->
                    listener.onSuccess(
                        result
                    )
                }
            )
        )
    }

    override fun addOnSuccessListener(listener: OnSuccessListener<in TResult?>): Task<TResult?> {
        return HmsTask<TResult?>(task.addOnSuccessListener { result ->
            listener.onSuccess(
                result
            )
        })
    }

    override fun addOnSuccessListener(
        activity: Activity,
        listener: OnSuccessListener<in TResult?>
    ): Task<TResult?> {
        return HmsTask<TResult?>(
            task.addOnSuccessListener(
                activity
            ) { result -> listener.onSuccess(result) }
        )
    }

    override fun <TContinuationResult> continueWith(continuation: Continuation<TResult?, TContinuationResult?>): Task<TContinuationResult?> {
        return HmsTask<TContinuationResult?>(
            task.continueWith { task ->
                continuation.then(
                    HmsTask<TResult?>(task)
                )
            }
        )
    }

    override fun <TContinuationResult> continueWith(
        executor: Executor,
        continuation: Continuation<TResult?, TContinuationResult?>
    ): Task<TContinuationResult?> {
        return HmsTask<TContinuationResult?>(
            task.continueWith<TContinuationResult?>(
                executor,
                com.huawei.hmf.tasks.Continuation<TResult?, TContinuationResult?> { task ->
                    continuation.then(
                        HmsTask<TResult?>(
                            task
                        )
                    )
                }
            )
        )
    }

    override fun <TContinuationResult> continueWithTask(continuation: Continuation<TResult?, Task<TContinuationResult?>?>): Task<TContinuationResult?> {
        return HmsTask<TContinuationResult?>(
            task.continueWithTask<TContinuationResult?> { task ->
                (continuation.then(
                    HmsTask<TResult?>(task)
                ) as HmsTask<TContinuationResult?>).task
            }
        )
    }

    override fun <TContinuationResult> continueWithTask(
        executor: Executor,
        continuation: Continuation<TResult?, Task<TContinuationResult?>?>
    ): Task<TContinuationResult?> {
        return HmsTask<TContinuationResult?>(
            task.continueWithTask<TContinuationResult?>(
                executor,
                com.huawei.hmf.tasks.Continuation<TResult?, com.huawei.hmf.tasks.Task<TContinuationResult?>?> { task ->
                    (continuation.then(
                        HmsTask<TResult?>(
                            task
                        )
                    ) as HmsTask<TContinuationResult?>).task
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
        return task.getResultThrowException(exceptionType)
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
        return HmsTask<TContinuationResult?>(
            task.onSuccessTask<TContinuationResult?>(
                executor,
                com.huawei.hmf.tasks.SuccessContinuation<TResult, TContinuationResult?> {
                    ((successContinuation.then(it) as HmsTask<TContinuationResult?>?)!!).task
                }
            )
        )
    }

    override fun <TContinuationResult> onSuccessTask(successContinuation: SuccessContinuation<TResult?, TContinuationResult?>): Task<TContinuationResult?> {
        return HmsTask<TContinuationResult?>(
            task.onSuccessTask {
                ((successContinuation.then(it) as HmsTask<TContinuationResult?>?)!!).task
            }
        )
    }

    companion object {
        fun <TResult> com.huawei.hmf.tasks.Task<TResult>.toHMSTask(): HmsTask<TResult> {
            return HmsTask<TResult>(this)
        }
    }
}