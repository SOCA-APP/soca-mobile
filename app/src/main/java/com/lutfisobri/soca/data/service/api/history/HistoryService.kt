package com.lutfisobri.soca.data.service.api.history

import com.lutfisobri.soca.data.network.response.history.DetailHistoryResponse
import com.lutfisobri.soca.data.network.response.history.HistoryResponse
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HistoryService {
    @GET("/history")
    suspend fun getHistories(
        @Query("page") page: Int?,
        @Query("perPage") size: Int?
    ): HistoryResponse

    @GET("/history/{id}")
    fun detailHistory(
        @Path("id") id: Int
    ): Call<DetailHistoryResponse>
}