package com.lutfisobri.soca.data.repository.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.lutfisobri.soca.data.network.request.favorite.FavoriteRequest
import com.lutfisobri.soca.data.network.request.pagination.PaginationRequest
import com.lutfisobri.soca.data.network.response.favorite.FavoriteResponse
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import com.lutfisobri.soca.data.paging.favorite.FavoritePagingSource
import com.lutfisobri.soca.data.service.api.favorite.FavoriteService
import com.lutfisobri.soca.utils.retrofit.RetrofitCallback

class FavoriteRepository(private val favoriteService: FavoriteService) {
    private val _addFavorite = MutableLiveData<FavoriteResponse>()
    val addFavorite = _addFavorite

    private val _removeFavorite = MutableLiveData<FavoriteResponse>()
    val removeFavorite = _removeFavorite

    private val _error = MutableLiveData<String?>()
    val error = _error

    fun getFavorites(paginationRequest: PaginationRequest): LiveData<PagingData<HistoryResponseResult>> {
        val perPage = paginationRequest.perPage ?: 10
        return Pager(
            config = PagingConfig(
                pageSize = perPage,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FavoritePagingSource(favoriteService, perPage) }
        ).liveData
    }

    fun addFavorite(favoriteRequest: FavoriteRequest) {
        favoriteService.addFavorite(favoriteRequest)
            .enqueue(object : RetrofitCallback<FavoriteResponse>(FavoriteResponse::class.java) {
                override fun onSuccess(data: FavoriteResponse?) {
                    _addFavorite.postValue(data!!)
                }

                override fun onError(error: String?) {
                    _error.postValue(error)
                }
            })
    }

    fun removeFavorite(favoriteRequest: FavoriteRequest) {
        favoriteService.removeFavorite(favoriteRequest)
            .enqueue(object : RetrofitCallback<FavoriteResponse>(FavoriteResponse::class.java) {
                override fun onSuccess(data: FavoriteResponse?) {
                    _removeFavorite.postValue(data!!)
                }

                override fun onError(error: String?) {
                    _error.postValue(error)
                }
            })
    }
}