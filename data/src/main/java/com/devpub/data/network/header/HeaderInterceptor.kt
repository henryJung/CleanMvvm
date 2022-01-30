package com.devpub.data.network.header

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val request = origin.newBuilder()
            .header("Content-Type", "application/json")
            .header("accept"," application/json")
            .method(origin.method, origin.body)
            .build()

        return chain.proceed(request)
    }
}