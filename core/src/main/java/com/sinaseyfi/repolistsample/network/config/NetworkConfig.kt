package com.sinaseyfi.repolistsample.network.config

import com.sinaseyfi.repolistsample.network.NetworkConfiguration
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class NetworkConfig @Inject constructor(
    private val mainInterceptor: MainInterceptor
): NetworkConfiguration {
    override fun getInterceptors(): List<Interceptor> =
        listOf(
            mainInterceptor,
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        )
}