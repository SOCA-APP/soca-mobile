package com.lutfisobri.soca.data.network.response.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.lutfisobri.soca.data.network.response.BaseResponse
import kotlinx.parcelize.Parcelize

data class HistoryResponse(
    @field:SerializedName("data")
    val data: List<HistoryResponseResult>? = null
): BaseResponse()

@Parcelize
data class HistoryResponseResult(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("label")
    val label: String,

    @field:SerializedName("confidenceScore")
    val confidenceScore: Double,

    @field:SerializedName("isFavorite")
    val isFavorite: Boolean,

    @field:SerializedName("createdAt")
    val createdAt: String
): Parcelable