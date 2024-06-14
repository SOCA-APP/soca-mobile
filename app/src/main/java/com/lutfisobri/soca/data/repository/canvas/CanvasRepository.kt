package com.lutfisobri.soca.data.repository.canvas

import android.net.Uri
import androidx.core.net.toFile
import androidx.lifecycle.MutableLiveData
import com.lutfisobri.soca.data.network.response.canvas.CanvasResponse
import com.lutfisobri.soca.data.service.api.canvas.CanvasService
import com.lutfisobri.soca.utils.retrofit.RetrofitCallback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

class CanvasRepository(private val canvasService: CanvasService) {
    private val _predict = MutableLiveData<CanvasResponse>()
    val predict = _predict

    private val _error = MutableLiveData<String?>()
    val error = _error

    fun predict(file: Uri) {
        canvasService.predict(request(file))
            .enqueue(object : RetrofitCallback<CanvasResponse>(CanvasResponse::class.java) {
                override fun onSuccess(data: CanvasResponse?) {
                    _predict.postValue(data!!)
                }

                override fun onError(error: String?) {
                    _error.postValue(error)
                }
            })
    }

    private fun request(file: Uri): MultipartBody.Part {
        val image = file.toFile()
        val requestFile = image.asRequestBody("image/*".toMediaType())
        val body = MultipartBody.Part.createFormData("image", image.name, requestFile)
        return body
    }
}