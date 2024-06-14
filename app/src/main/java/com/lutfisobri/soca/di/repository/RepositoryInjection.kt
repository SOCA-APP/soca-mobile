package com.lutfisobri.soca.di.repository

import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.data.repository.auth.AuthRepository
import com.lutfisobri.soca.data.repository.favorite.FavoriteRepository
import com.lutfisobri.soca.data.repository.history.HistoryRepository
import com.lutfisobri.soca.data.service.api.auth.AuthService
import com.lutfisobri.soca.data.service.api.favorite.FavoriteService
import com.lutfisobri.soca.data.service.api.history.HistoryService
import com.lutfisobri.soca.di.network.NetworkInjection

object RepositoryInjection {
    fun provideAuthRepository(): AuthRepository {
        val authService = NetworkInjection().createService(AuthService::class.java)
        return AuthRepository(authService)
    }

    fun provideHistoryRepository(authPreference: AuthPreference): HistoryRepository {
        val historyService = NetworkInjection(authPreference).createService(HistoryService::class.java)
        return HistoryRepository(historyService)
    }

    fun provideFavoriteRepository(authPreference: AuthPreference): FavoriteRepository {
        val favoriteService = NetworkInjection(authPreference).createService(FavoriteService::class.java)
        return FavoriteRepository(favoriteService)
    }
}