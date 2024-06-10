package com.lutfisobri.soca.ui.result

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lutfisobri.soca.data.network.request.favorite.FavoriteRequest
import com.lutfisobri.soca.data.network.response.favorite.FavoriteResponse
import com.lutfisobri.soca.data.network.response.history.DetailHistoryResponse
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.di.repository.RepositoryInjection

class ResultViewModel(private val authPreference: AuthPreference): ViewModel() {
    private val _add = MutableLiveData<FavoriteResponse>()
    val result = _add

    private val _remove = MutableLiveData<FavoriteResponse>()
    val remove = _remove

    private val _detail = MutableLiveData<DetailHistoryResponse>()
    val detail = _detail

    private val _error = MutableLiveData<String>()
    val error = _error

    fun addFavorite(id: Int) {
        val request = FavoriteRequest(id)
        val repository = RepositoryInjection.provideFavoriteRepository(authPreference)
        repository.addFavorite(request)
        repository.addFavorite.observeForever {
            _add.postValue(it)

        }
        repository.error.observeForever {
            _error.postValue(it)
        }
    }

    fun removeFavorite(id: Int) {
        val request = FavoriteRequest(id)
        val repository = RepositoryInjection.provideFavoriteRepository(authPreference)
        repository.removeFavorite(request)
        repository.removeFavorite.observeForever {
            _remove.postValue(it)
        }
        repository.error.observeForever {
            _error.postValue(it)
        }
    }

    fun detail(id: Int) {
        val repository = RepositoryInjection.provideHistoryRepository(authPreference)
        repository.detailHistory(id)
        repository.detailHistory.observeForever {
            _detail.postValue(it)
        }
        repository.error.observeForever {
            _error.postValue(it)
        }
    }
}