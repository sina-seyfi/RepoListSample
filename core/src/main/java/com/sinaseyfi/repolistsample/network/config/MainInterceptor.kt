package com.sinaseyfi.repolistsample.network.config

import com.sinaseyfi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class MainInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        requestBuilder.addHeader(BuildConfig.ACCEPT_HEADER_TITLE, BuildConfig.ACCEPT_HEADER_VALUE)
        requestBuilder.addHeader("User-Agent", "Android")
        return chain.proceed(requestBuilder.build())
    }
}