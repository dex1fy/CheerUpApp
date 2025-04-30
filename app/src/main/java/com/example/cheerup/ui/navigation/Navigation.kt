package com.example.cheerup.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cheerup.MainScreen
import com.example.cheerup.Screen
import com.example.cheerup.SupabaseAuthViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationRoutes.MAIN) {
        composable(NavigationRoutes.MAIN) {
            val authViewModel: SupabaseAuthViewModel = viewModel()

            MainScreen(authViewModel , navController)
        }
        composable(NavigationRoutes.SCREEN) {
            val authViewModel: SupabaseAuthViewModel = viewModel()

            Screen(authViewModel, navController)
        }
    }
}
