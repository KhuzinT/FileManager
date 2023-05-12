package com.example.filemanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.filemanager.navigation.files.FilesNavHost
import com.example.filemanager.screens.app.AppViewModel
import com.example.filemanager.screens.permission.PermissionScreen
import com.example.filemanager.screens.splash.SplashScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    appViewModel: AppViewModel
) {
    NavHost(navController = navController, startDestination = MainNavGraph.Splash.name) {
        composable(route = MainNavGraph.Splash.name) {
            SplashScreen(
                navigateToPermission = {
                    navController.popBackStack()
                    navController.navigate(MainNavGraph.Permission.name)
                },
                navigateToMain = {
                    navController.popBackStack()
                    navController.navigate(MainNavGraph.Files.name)
                },
                appViewModel = appViewModel
            )
        }
        composable(route = MainNavGraph.Permission.name) {
            PermissionScreen(
                navigateToSplash = {
                    navController.popBackStack()
                    navController.navigate(MainNavGraph.Splash.name)
                }
            )
        }
        composable(route = MainNavGraph.Files.name) {
            FilesNavHost()
        }
    }
}