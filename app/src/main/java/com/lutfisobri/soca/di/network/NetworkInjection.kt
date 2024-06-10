package com.lutfisobri.soca.di.network

import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.utils.retrofit.HeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkInjection(private val authPreference: AuthPreference? = null) {
    private val baseUrl = "http://10.0.2.2:3000/"

    private val builder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())

    private fun client(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (authPreference != null) {
            builder.addInterceptor(HeaderInterceptor(authPreference))
        }
        return builder.build()
    }

    fun <T> createService(service: Class<T>): T {
        return builder.client(client())
            .build()
            .create(service)
    }
}