package com.lutfisobri.soca.data.network.request.auth

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String
)