package com.lutfisobri.soca.config.jwt

import com.auth0.android.jwt.JWT
import com.lutfisobri.soca.data.models.UserModel

object JwtConfig {
    fun parseToken(token: String): UserModel {
        val jwt = JWT(token)
        val id = jwt.getClaim("id").asInt()
        val fullName = jwt.getClaim("fullName").asString()
        val email = jwt.getClaim("email").asString()
        return UserModel(id, fullName, email)
    }
}