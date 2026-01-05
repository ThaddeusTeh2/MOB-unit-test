package com.team.moblocation.domain.auth

data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword: String
)