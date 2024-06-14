package com.lutfisobri.soca.data.network.response

import com.google.gson.annotations.SerializedName

abstract class BaseResponse{
    @field:SerializedName("status")
    val status: Int = 0

    @field:SerializedName("message")
    val message: String? = null
}