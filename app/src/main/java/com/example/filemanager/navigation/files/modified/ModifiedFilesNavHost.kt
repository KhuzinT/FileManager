package com.example.filemanager.navigation.files.modified

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filemanager.R
import com.example.filemanager.navigation.files.FilesNavGraph
import com.example.filemanager.screens.files.modified.ModifiedFilesScreen
import com.example.filemanager.screens.files.type.FilesTypeScreen

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
            ModifiedFilesScreen(navController = navController)
        }
        composable(route = ModifiedFilesNavGraph.ImagesFiles.name) {
            FilesTypeScreen(
                desc = R.string.modified_files_screen_images_en,
                extensions = listOf("jpeg", "png", "jpg", "img"),
                navigateBefore = {
                    navController.popBackStack()
                    navController.navigate(ModifiedFilesNavGraph.ModifiedFiles.name)
                }
            )
        }
        composable(route = ModifiedFilesNavGraph.MoviesFiles.name) {
            FilesTypeScreen(
                desc = R.string.modified_files_screen_movies_en,
                extensions = listOf("mp4"),
                navigateBefore = {
                    navController.popBackStack()
                    navController.navigate(ModifiedFilesNavGraph.ModifiedFiles.name)
                }
            )
        }
        composable(route = ModifiedFilesNavGraph.DocsFiles.name) {
            FilesTypeScreen(
                desc = R.string.modified_files_screen_docs_en,
                extensions = listOf("pdf", "docx"),
                navigateBefore = {
                    navController.popBackStack()
                    navController.navigate(ModifiedFilesNavGraph.ModifiedFiles.name)
                }
            )
        }
        composable(route = ModifiedFilesNavGraph.AudioFiles.name) {
            FilesTypeScreen(
                desc = R.string.modified_files_screen_audio_en,
                extensions = listOf("mp3"),
                navigateBefore = {
                    navController.popBackStack()
                    navController.navigate(ModifiedFilesNavGraph.ModifiedFiles.name)
                }
            )
        }
    }
}