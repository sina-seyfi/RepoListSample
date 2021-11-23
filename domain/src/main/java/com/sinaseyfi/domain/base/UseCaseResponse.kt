package com.sinaseyfi.domain.base

open class UseCaseResponse<Res>(
    open var onSuccess: (suspend (result: UseCaseResult<Res>) -> Unit)? = null,
    open var onError: (suspend (exception: Throwable) -> Unit)? = null,
    open var onFinish: (suspend () -> Unit)? = null,
    open var onStart:(suspend () -> Unit)? = null
)