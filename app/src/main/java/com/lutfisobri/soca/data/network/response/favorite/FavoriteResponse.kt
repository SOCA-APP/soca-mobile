package com.lutfisobri.soca.data.network.response.favorite

import com.google.gson.annotations.SerializedName
import com.lutfisobri.soca.data.network.response.BaseResponse
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult

data class FavoriteResponse(
    @field:SerializedName("data")
    val data: List<HistoryResponseResult>? = null
) : BaseResponse()