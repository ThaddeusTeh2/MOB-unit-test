package com.team.moblocation.data.auth

import com.team.moblocation.domain.auth.AuthResult
import com.team.moblocation.domain.auth.LoginRequest
import com.team.moblocation.domain.auth.RegisterRequest

interface AuthRepository {
    suspend fun login(request: LoginRequest): AuthResult
    suspend fun register(request: RegisterRequest): AuthResult
}