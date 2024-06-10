package com.lutfisobri.soca.data.service.api.canvas

import com.lutfisobri.soca.data.network.response.canvas.CanvasResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CanvasService {
    @Multipart
    @POST("/predict")
    fun predict(
        @Part file: MultipartBody.Part
    ): Call<CanvasResponse>
}