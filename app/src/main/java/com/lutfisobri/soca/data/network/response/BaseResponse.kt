package com.lutfisobri.soca.data.network.response

import com.google.gson.annotations.SerializedName

abstract class BaseResponse{
    @field:SerializedName("status")
    var status: Int = 0

    @field:SerializedName("message")
    var message: String? = null
}