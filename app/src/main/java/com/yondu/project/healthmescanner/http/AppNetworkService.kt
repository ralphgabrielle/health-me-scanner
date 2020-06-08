package com.yondu.project.healthmescanner.http

import com.yondu.project.apphealthme.http.BodyInterceptor
import com.yondu.project.apphealthme.http.Wrapper
import com.yondu.project.healthmescanner.BuildConfig
import com.yondu.project.healthmescanner.data.body.LoginBody
import com.yondu.project.healthmescanner.data.local.Token
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface AppNetworkService {

    @POST("api/login")
    suspend fun login(@Body loginBody: LoginBody): Wrapper<Token>

    companion object {
        operator fun invoke(): AppNetworkService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(BodyInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AppNetworkService::class.java)
        }
    }
}