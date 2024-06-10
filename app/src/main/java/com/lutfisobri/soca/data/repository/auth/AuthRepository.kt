package com.lutfisobri.soca.data.repository.auth

import androidx.lifecycle.MutableLiveData
import com.lutfisobri.soca.data.network.request.auth.LoginRequest
import com.lutfisobri.soca.data.network.request.auth.RegisterRequest
import com.lutfisobri.soca.data.network.response.BaseResponse
import com.lutfisobri.soca.data.network.response.auth.LoginResponse
import com.lutfisobri.soca.data.network.response.auth.RegisterResponse
import com.lutfisobri.soca.data.service.api.auth.AuthService
import com.lutfisobri.soca.utils.retrofit.RetrofitCallback

class AuthRepository(private val authService: AuthService) {
    private val _login = MutableLiveData<LoginResponse>()
    val login: MutableLiveData<LoginResponse> = _login

    private val _register = MutableLiveData<RegisterResponse>()
    val register: MutableLiveData<RegisterResponse> = _register

    private val _logout = MutableLiveData<BaseResponse>()
    val logout: MutableLiveData<BaseResponse> = _logout

    private val _error = MutableLiveData<String?>()
    val error: MutableLiveData<String?> = _error

    fun register(registerRequest: RegisterRequest) {
        authService.register(registerRequest).enqueue(object :
            RetrofitCallback<RegisterResponse>(RegisterResponse::class.java) {
            override fun onSuccess(data: RegisterResponse?) {
                _register.postValue(data!!)
            }

            override fun onError(error: String?) {
                _error.postValue(error)
            }
        })
    }

    fun login(loginRequest: LoginRequest) {
        authService.login(loginRequest).enqueue(object : RetrofitCallback<LoginResponse>(LoginResponse::class.java) {
            override fun onSuccess(data: LoginResponse?) {
                _login.postValue(data!!)
            }

            override fun onError(error: String?) {
                _error.postValue(error)
            }
        })
    }

    fun logout() {
        authService.logout().enqueue(object : RetrofitCallback<BaseResponse>(BaseResponse::class.java) {
            override fun onSuccess(data: BaseResponse?) {
                _logout.postValue(data!!)
            }

            override fun onError(error: String?) {
                _error.postValue(error)
            }
        })
    }
}