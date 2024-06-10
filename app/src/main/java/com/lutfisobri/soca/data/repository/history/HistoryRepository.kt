package com.lutfisobri.soca.data.repository.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.lutfisobri.soca.data.network.request.pagination.PaginationRequest
import com.lutfisobri.soca.data.network.response.history.DetailHistoryResponse
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import com.lutfisobri.soca.data.paging.history.HistoryPagingSource
import com.lutfisobri.soca.data.service.api.history.HistoryService
import com.lutfisobri.soca.utils.retrofit.RetrofitCallback

class HistoryRepository(private val historyService: HistoryService) {
    private val _detailHistory = MutableLiveData<DetailHistoryResponse>()
    val detailHistory = _detailHistory

    private val _error = MutableLiveData<String?>()
    val error = _error

    fun getHistories(paginationRequest: PaginationRequest): LiveData<PagingData<HistoryResponseResult>> {
        val perPage = paginationRequest.perPage ?: 10
        return Pager(
            config = PagingConfig(
                pageSize = perPage,
                enablePlaceholders = false
            ),
            initialKey = 1,
            pagingSourceFactory = { HistoryPagingSource(historyService, perPage) }
        ).liveData
    }

    fun detailHistory(id: Int) {
        historyService.detailHistory(id).enqueue(object: RetrofitCallback<DetailHistoryResponse>(DetailHistoryResponse::class.java) {
            override fun onSuccess(data: DetailHistoryResponse?) {
                println("data: $data")
                _detailHistory.postValue(data!!)
            }

            override fun onError(error: String?) {
                _error.postValue(error)
            }
        })
    }
}