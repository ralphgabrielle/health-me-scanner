package com.yondu.project.apphealthme.http

import okhttp3.Interceptor
import okhttp3.Response

/*
* Created by Ralph Gabrielle Orden on May 21 2020
*/

class BodyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        return if (response.code == 204 || response.code == 205) {
            response
                .newBuilder()
                .code(200)
                .body(response.body)
                .build()
        } else {
            response
        }
    }
}