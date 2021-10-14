package com.mw.news.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mw.news.ui.home.HomeScreen
import com.mw.news.ui.home.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.HomeScreen.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(navController = navController, viewModel)
        }
    }
}