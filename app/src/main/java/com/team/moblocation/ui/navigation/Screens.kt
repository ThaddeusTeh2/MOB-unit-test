package com.team.moblocation.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
}

