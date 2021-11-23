package com.sinaseyfi.domain.base

import com.sinaseyfi.log.Logger
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

open class AsynchronousUseCase<Res> : UseCase<Res> {

    protected open val defaultDispatcher = Dispatchers.IO

    protected var baseResponse: UseCaseResponse<Res>? = null

    protected var response: UseCaseResponse<Res>? =
        UseCaseResponse()
        private set

    protected suspend fun dispatchStart() {
        baseResponse?.onStart?.invoke()
        response?.onStart?.invoke()
    }

    protected suspend fun dispatchFinish() {
        baseResponse?.onFinish?.invoke()
        response?.onFinish?.invoke()
    }

    protected suspend fun dispatchError(exception: Throwable) {
        baseResponse?.onError?.invoke(exception)
        response?.onError?.invoke(exception)
    }

    protected suspend fun dispatchSuccess(result: UseCaseResult<Res>) {
        baseResponse?.onSuccess?.invoke(result)
        response?.onSuccess?.invoke(result)
    }

    @Inject
    protected lateinit var logger: Logger

}