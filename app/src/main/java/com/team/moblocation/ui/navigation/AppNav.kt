package com.team.moblocation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team.moblocation.ui.screens.auth.LoginScreen
import com.team.moblocation.ui.screens.auth.RegisterScreen
import com.team.moblocation.ui.screens.home.HomeScreen

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Login
    ) {
        composable<Screen.Home> {
            HomeScreen()
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                },
                onSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onLoginClick = {
                    navController.popBackStack()
                },
                onSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
    }
}