package com.sinaseyfi.remote.base

import okhttp3.Headers

data class Result<T>(val data: T? = null, val headers: Headers? = null)