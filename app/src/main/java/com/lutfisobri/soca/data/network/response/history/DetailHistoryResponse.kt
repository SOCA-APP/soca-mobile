package com.lutfisobri.soca.data.network.response.history

import com.google.gson.annotations.SerializedName
import com.lutfisobri.soca.data.network.response.BaseResponse

data class DetailHistoryResponse(
    @field:SerializedName("data")
    val data: HistoryResponseResult
): BaseResponse()