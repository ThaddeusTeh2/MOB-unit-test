package com.team.moblocation.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.moblocation.data.auth.AuthRepository
import com.team.moblocation.data.repo.IUserRepo
import com.team.moblocation.domain.auth.AuthResult
import com.team.moblocation.domain.auth.LoginRequest
import com.team.moblocation.domain.auth.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
//    private val repo: AuthRepository
    private val repo: IUserRepo
) : ViewModel() {

    private val _greetings = MutableStateFlow("")
    val greetings = _greetings.asStateFlow()

    fun greet(name: String) {
        viewModelScope.launch{
            delay(1000)
            _greetings.value = "Hello $name"
        }
    }

    fun fetchUser(): String {
        val user = repo.getUser()
        _greetings.value = "Hello $user"
        return user
    }

    fun validate(email: String, pass: String): String? {
        return try {
            require(email.isNotBlank() && email == "email@a.com") { "Invalid Email" }
            require(pass.isNotBlank() && pass == "password") {"Invalid Password"}
            null
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    private val _authState = MutableStateFlow<AuthResult?>(null)
    val authState: StateFlow<AuthResult?> = _authState

//    fun login(email: String, password: String) {
//        viewModelScope.launch {
//            _authState.value = AuthResult.Loading
//            _authState.value = repo.login(LoginRequest(email, password))
//        }
//    }
//
//    fun register(email: String, password: String, confirm: String) {
//        viewModelScope.launch {
//            _authState.value = AuthResult.Loading
//            _authState.value = repo.register(
//                RegisterRequest(email, password, confirm)
//            )
//        }
//    }
}