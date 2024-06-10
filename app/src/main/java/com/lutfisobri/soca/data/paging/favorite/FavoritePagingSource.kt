package com.lutfisobri.soca.data.paging.favorite

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lutfisobri.soca.data.network.response.history.HistoryResponseResult
import com.lutfisobri.soca.data.service.api.favorite.FavoriteService

class FavoritePagingSource(private val favoriteService: FavoriteService, private val perPage: Int): PagingSource<Int, HistoryResponseResult>() {
    override fun getRefreshKey(state: PagingState<Int, HistoryResponseResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HistoryResponseResult> {
        return try {
            val nextPageNumber = params.key ?: STARTING_PAGE_INDEX
            val response = favoriteService.getFavorites(nextPageNumber, perPage)
            LoadResult.Page(
                data = response.data!!,
                prevKey = if (nextPageNumber == STARTING_PAGE_INDEX) null else nextPageNumber - 1,
                nextKey = if (response.data.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}