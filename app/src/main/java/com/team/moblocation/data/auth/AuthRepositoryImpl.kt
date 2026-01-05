package com.team.moblocation.data.auth

import com.team.moblocation.domain.auth.AuthResult
import com.team.moblocation.domain.auth.LoginRequest
import com.team.moblocation.domain.auth.RegisterRequest

class AuthRepositoryImpl : AuthRepository {

    override suspend fun login(request: LoginRequest): AuthResult {
        if (request.email.isBlank() || request.password.isBlank()) {
            return AuthResult.Error("Fields cannot be empty")
        }
        return AuthResult.Success
    }

    override suspend fun register(request: RegisterRequest): AuthResult {
        if (request.password != request.confirmPassword) {
            return AuthResult.Error("Passwords do not match")
        }
        return AuthResult.Success
    }
}