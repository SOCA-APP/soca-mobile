package com.lutfisobri.soca.utils.retrofit

import com.lutfisobri.soca.data.preference.auth.AuthPreference
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val authPreference: AuthPreference): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val header = chain.request().newBuilder()
            .header("Accept", "application/json")

        if (authPreference.getToken().isNullOrEmpty().not()) {
            header.addHeader("Authorization", "Bearer ${authPreference.getToken()}")
        }

        val request = header.build()

        return chain.proceed(request)
    }
}