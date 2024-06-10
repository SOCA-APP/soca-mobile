package com.lutfisobri.soca.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lutfisobri.soca.data.network.request.pagination.PaginationRequest
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.di.repository.RepositoryInjection

class FavoriteViewModel(private val authPreference: AuthPreference): ViewModel() {
    private val _favorite = MutableLiveData<PagingData<HistoryResponseResult>>()
    val favorite = _favorite

    fun favorite() {
        val repository = RepositoryInjection.provideFavoriteRepository(authPreference)
        repository.getFavorites(PaginationRequest(1, 10)).cachedIn(viewModelScope).observeForever {
            _favorite.postValue(it)
        }
    }
}