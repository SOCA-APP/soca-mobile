package com.lutfisobri.soca.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lutfisobri.soca.data.network.request.auth.RegisterRequest
import com.lutfisobri.soca.data.network.response.auth.RegisterResponse
import com.lutfisobri.soca.di.repository.RepositoryInjection

class RegisterViewModel: ViewModel() {
    private val _register = MutableLiveData<RegisterResponse>()
    val register: MutableLiveData<RegisterResponse> = _register

    private val _error = MutableLiveData<RegisterResponse>()
    val error: MutableLiveData<RegisterResponse> = _error

    private val _apiError = MutableLiveData<String>()
    val apiError: MutableLiveData<String> = _apiError

    fun register(name: String, email: String, password: String) {
        val repository = RepositoryInjection.provideAuthRepository()
        repository.register(RegisterRequest(name, email, password))

        repository.register.observeForever {
            when(it.status) {
                200 -> {
                    _register.postValue(it)
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