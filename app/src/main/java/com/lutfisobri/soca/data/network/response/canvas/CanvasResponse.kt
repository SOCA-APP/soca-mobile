package com.lutfisobri.soca.data.network.response.canvas

import com.google.gson.annotations.SerializedName
import com.lutfisobri.soca.data.network.response.BaseResponse

data class CanvasResponse(
    @field:SerializedName("data")
    val data: CanvasResponseResult? = null
): BaseResponse()

data class CanvasResponseResult(
    @field:SerializedName("label")
    val label: String,

    @field:SerializedName("confidenceScore")
    val confidenceScore: Double
)