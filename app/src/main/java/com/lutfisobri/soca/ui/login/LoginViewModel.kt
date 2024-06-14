package com.lutfisobri.soca.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lutfisobri.soca.data.network.request.auth.LoginRequest
import com.lutfisobri.soca.data.network.response.auth.LoginResponse
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.di.repository.RepositoryInjection

class LoginViewModel(private val authPreference: AuthPreference): ViewModel() {
    private val _login = MutableLiveData<LoginResponse>()
    val login: MutableLiveData<LoginResponse> = _login

    private val _error = MutableLiveData<LoginResponse>()
    val error: MutableLiveData<LoginResponse> = _error

    private val _apiError = MutableLiveData<String>()
    val apiError: MutableLiveData<String> = _apiError

    fun login(email: String, password: String) {
        val repository = RepositoryInjection.provideAuthRepository()
        repository.login(LoginRequest(email, password))

        repository.login.observeForever {
            when(it.status) {
                200 -> {
                    _login.postValue(it)
                    it.data?.token?.let { token ->
                        authPreference.saveToken(token)
                    }
                }
                else -> {
                    _error.postValue(it)
                }
            }
        }
        repository.error.observeForever {
            _apiError.postValue(it)
        }
    }
}