package com.lutfisobri.soca.ui.dashboard

import androidx.lifecycle.ViewModel
import com.lutfisobri.soca.config.jwt.JwtConfig
import com.lutfisobri.soca.data.models.UserModel
import com.lutfisobri.soca.data.preference.auth.AuthPreference
import com.lutfisobri.soca.di.repository.RepositoryInjection

class DashboardViewModel(private val authPreference: AuthPreference): ViewModel() {
    fun logout() {
        val repository = RepositoryInjection.provideAuthRepository()
        repository.logout()
        authPreference.clearToken()
    }

    fun getUser(): UserModel = JwtConfig.parseToken(authPreference.getToken() ?: "")
}