package com.example.filemanager.navigation.files.all

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filemanager.navigation.files.FilesNavGraph
import com.example.filemanager.screens.files.all.AllFilesScreen

@Composable
fun AllFilesNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        route = FilesNavGraph.AllFilesHost.name,
        startDestination = AllFilesNavGraph.AllFiles.name
    ) {
        composable(route = AllFilesNavGraph.AllFiles.name) {
            AllFilesScreen()
        }
    }
}