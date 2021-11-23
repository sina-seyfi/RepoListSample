package com.sinaseyfi.repolistsample.network

import okhttp3.Interceptor

interface NetworkConfiguration {
    fun getInterceptors() : List<Interceptor>
}