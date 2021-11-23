package com.sinaseyfi.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

abstract class SingleUseCase<in Req, Res> : AsynchronousUseCase<Res>() {

    suspend fun perform(request: Req, base: UseCaseResponse<Res>? = null, func: UseCaseResponse<Res>.() -> Unit = {}) {
        baseResponse = base
        response?.let { func(it) }
        launchTask(request)
    }

    private suspend fun launchTask(request: Req) {
        dispatchStart()
        try {
            val job = CoroutineScope(defaultDispatcher).async { task(request) }
            dispatchSuccess(job.await())
        } catch (exception: Throwable) {
            if(response?.onError == null) {
                logger.error(exception)
            }
            dispatchError(exception)
        }
        dispatchFinish()
    }

    protected abstract suspend fun task(request: Req): UseCaseResult<Res>

}
