package com.sinaseyfi.domain.base

import kotlinx.coroutines.flow.Flow

abstract class BaseFlowUseCase<Req, Res> {

    abstract fun perform(request: Req): Flow<Res>

}