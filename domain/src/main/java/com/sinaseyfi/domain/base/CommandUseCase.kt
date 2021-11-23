package com.sinaseyfi.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

abstract class CommandUseCase : AsynchronousUseCase<Any?>() {

    suspend fun perform(base: UseCaseResponse<Any?>? = null, func: UseCaseResponse<Any?>.() -> Unit = {}) {
        baseResponse = base
        response?.let { func(it) }
        launchTask()
    }

    private suspend fun launchTask() {
        dispatchStart()
        try {
            val job = CoroutineScope(defaultDispatcher).async { task() }
            dispatchSuccess(job.await())
        } catch (exception: Throwable) {
            if(response?.onError == null) {
                logger.error(exception)
            }
            dispatchError(exception)
        }
        dispatchFinish()
    }

    protected abstract suspend fun task(): UseCaseConsumerResult

}
