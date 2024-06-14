package com.lutfisobri.soca.data.network.response.canvas

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.lutfisobri.soca.data.network.response.BaseResponse
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import kotlinx.parcelize.Parcelize

@Parcelize
data class CanvasResponse(
    @field:SerializedName("data")
    val data: HistoryResponseResult? = null
): BaseResponse(), Parcelable