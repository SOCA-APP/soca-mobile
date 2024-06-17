package com.lutfisobri.soca.di.network

import com.lutfisobri.soca.BuildConfig
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.data.service.api.canvas.CanvasService
import com.lutfisobri.soca.utils.retrofit.HeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkInjection(private val authPreference: AuthPreference? = null) {
    private val builder = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    private fun client(timeout: Long? = null): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (authPreference != null) {
            builder.addInterceptor(HeaderInterceptor(authPreference))
        }
        if (timeout != null) {
            builder.connectTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS)
            builder.readTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS)
            builder.writeTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS)
        }
        return builder.build()
    }

    fun <T> createService(service: Class<T>): T {
        return builder.client(client())
            .build()
            .create(service)
    }

    fun canvasService(): CanvasService {
        return builder.client(client(1800))
            .build()
            .create(CanvasService::class.java)
    }
}