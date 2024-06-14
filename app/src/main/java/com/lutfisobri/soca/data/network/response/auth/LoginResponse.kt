package com.lutfisobri.soca.data.network.response.auth

import com.google.gson.annotations.SerializedName
import com.lutfisobri.soca.data.network.response.BaseResponse

data class LoginResponse(
    @field:SerializedName("data")
    val data: LoginResponseResult? = null
): BaseResponse()

data class LoginResponseResult(
    @field:SerializedName("token")
    val token: String,

    @field:SerializedName("tokenType")
    val tokenType: String,

    @field:SerializedName("expiresIn")
    val expiresIn: Int
)