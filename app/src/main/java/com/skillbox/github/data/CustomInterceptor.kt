package com.skillbox.github.data

import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor(
    private val headerName: String,
    private val headerValue: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifyRequest = originalRequest.newBuilder()
            .addHeader(headerName, headerValue)
            .build()

        val response = chain.proceed(modifyRequest)
        return response
    }
}