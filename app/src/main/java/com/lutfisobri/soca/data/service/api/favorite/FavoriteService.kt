package com.lutfisobri.soca.data.service.api.favorite

import com.lutfisobri.soca.data.network.request.favorite.FavoriteRequest
import com.lutfisobri.soca.data.network.response.favorite.FavoriteResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Query

interface FavoriteService {
    @GET("/favorite")
    suspend fun getFavorites(
        @Query("page") page: Int,
        @Query("perPage") size: Int
    ): FavoriteResponse

    @POST("/favorite")
    fun addFavorite(@Body request: FavoriteRequest): Call<FavoriteResponse>

    @HTTP(method = "DELETE", path = "/favorite", hasBody = true)
    fun removeFavorite(@Body request: FavoriteRequest): Call<FavoriteResponse>
}