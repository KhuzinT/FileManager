package com.example.filemanager.screens.app

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.filemanager.navigation.MainNavHost

@Composable
fun AppScreen(
    navController: NavHostController,
    appViewModel: AppViewModel = viewModel()
) {
    MainNavHost(
        navController = navController, appViewModel = appViewModel
    )
}