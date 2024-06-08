package com.lutfisobri.soca.di.repository

import com.lutfisobri.soca.data.repository.auth.AuthRepository
import com.lutfisobri.soca.data.service.api.auth.AuthService
import com.lutfisobri.soca.di.network.NetworkInjection

object RepositoryInjection {
    fun provideAuthRepository(): AuthRepository {
        val authService = NetworkInjection().createService(AuthService::class.java)
        return AuthRepository(authService)
    }
}