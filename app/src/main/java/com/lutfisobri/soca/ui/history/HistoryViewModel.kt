package com.lutfisobri.soca.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lutfisobri.soca.data.network.request.pagination.PaginationRequest
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.di.repository.RepositoryInjection

class HistoryViewModel(private val authPreference: AuthPreference): ViewModel() {
    private val _history = MutableLiveData<PagingData<HistoryResponseResult>>()
    val history = _history

    fun history() {
        val repository = RepositoryInjection.provideHistoryRepository(authPreference)
        repository.getHistories(PaginationRequest(1, 10)).cachedIn(viewModelScope).observeForever {
            _history.postValue(it)
        }
    }
}