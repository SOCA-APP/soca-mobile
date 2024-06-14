package com.lutfisobri.soca.ui.canvas

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lutfisobri.soca.data.network.response.canvas.CanvasResponse
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.di.repository.RepositoryInjection

class CanvasViewModel(private val authPreference: AuthPreference): ViewModel() {
    private val _predict = MutableLiveData<HistoryResponseResult>()
    val predict = _predict

    private val _error = MutableLiveData<CanvasResponse>()
    val error = _error

    private val _apiError = MutableLiveData<String?>()
    val api = _apiError

    fun predict(file: Uri) {
        val repository = RepositoryInjection.provideCanvasRepository(authPreference)
        repository.predict(file)
        repository.predict.observeForever {
            if (it.status == 200) {
                _predict.postValue(it.data!!)
            } else {
                _error.postValue(it)
            }
        }
        repository.error.observeForever {
            _apiError.postValue(it)
        }
    }
}