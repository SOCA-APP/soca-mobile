package com.lutfisobri.soca.di.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkInjection {
    private val baseUrl = ""

    private val builder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())

    private fun client(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }

    fun <T> createService(service: Class<T>): T {
        return builder.client(client())
            .build()
            .create(service)
    }
}