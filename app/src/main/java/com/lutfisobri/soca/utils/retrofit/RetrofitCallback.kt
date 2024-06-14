package com.lutfisobri.soca.utils.retrofit

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class RetrofitCallback<T>(private val clazz: Class<T>): Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            onSuccess(response.body())
        } else {
            try {
                val gson = Gson()
                val errorResponse = gson.fromJson(response.errorBody()?.string(), clazz)
                onSuccess(errorResponse)
            } catch (e: Exception) {
                onError(e.message)
            }
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onError(t.message)
    }

    abstract fun onSuccess(data: T?)

    abstract fun onError(error: String?)
}