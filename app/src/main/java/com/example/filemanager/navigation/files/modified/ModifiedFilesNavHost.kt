package com.example.filemanager.navigation.files.modified

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filemanager.navigation.files.FilesNavGraph
import com.example.filemanager.screens.files.modified.ModifiedFilesScreen

@Composable
fun ModifiedFilesNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        route = FilesNavGraph.ModifiedFilesHost.name,
        startDestination = ModifiedFilesNavGraph.ModifiedFiles.name
    ) {
        composable(route = ModifiedFilesNavGraph.ModifiedFiles.name) {
            ModifiedFilesScreen()
        }
    }
}