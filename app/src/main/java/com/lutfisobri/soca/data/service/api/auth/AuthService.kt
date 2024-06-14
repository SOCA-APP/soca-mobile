package com.lutfisobri.soca.data.service.api.auth

import com.lutfisobri.soca.data.network.request.auth.LoginRequest
import com.lutfisobri.soca.data.network.request.auth.RegisterRequest
import com.lutfisobri.soca.data.network.response.BaseResponse
import com.lutfisobri.soca.data.network.response.auth.LoginResponse
import com.lutfisobri.soca.data.network.response.auth.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("register")
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("logout")
    fun logout(): Call<BaseResponse>
}