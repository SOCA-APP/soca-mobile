package com.lutfisobri.soca.data.network.response.auth

import com.google.gson.annotations.SerializedName
import com.lutfisobri.soca.data.network.response.BaseResponse

data class RegisterResponse(
    @field:SerializedName("data")
    val data: Any? = null
): BaseResponse()