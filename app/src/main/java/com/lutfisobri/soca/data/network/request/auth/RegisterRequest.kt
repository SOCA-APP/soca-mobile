package com.lutfisobri.soca.data.network.request.auth

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)