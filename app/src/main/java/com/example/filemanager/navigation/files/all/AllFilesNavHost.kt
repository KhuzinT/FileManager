package com.example.filemanager.navigation.files.all

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filemanager.R
import com.example.filemanager.navigation.files.FilesNavGraph
import com.example.filemanager.navigation.files.modified.ModifiedFilesNavGraph
import com.example.filemanager.screens.files.all.AllFilesScreen
import com.example.filemanager.screens.files.type.FilesTypeScreen

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
            AllFilesScreen(navController = navController)
        }
        composable(route = AllFilesNavGraph.ImagesFiles.name) {
            FilesTypeScreen(
                desc = R.string.modified_files_screen_images_en,
                extensions = listOf("jpeg", "png", "jpg", "img"),
                navigateBefore = {
                    navController.popBackStack()
                    navController.navigate(AllFilesNavGraph.AllFiles.name)
                }
            )
        }
        composable(route = AllFilesNavGraph.MoviesFiles.name) {
            FilesTypeScreen(
                desc = R.string.modified_files_screen_movies_en,
                extensions = listOf("mp4"),
                navigateBefore = {
                    navController.popBackStack()
                    navController.navigate(AllFilesNavGraph.AllFiles.name)
                }
            )
        }
        composable(route = AllFilesNavGraph.DocsFiles.name) {
            FilesTypeScreen(
                desc = R.string.modified_files_screen_docs_en,
                extensions = listOf("pdf", "docx"),
                navigateBefore = {
                    navController.popBackStack()
                    navController.navigate(AllFilesNavGraph.AllFiles.name)
                }
            )
        }
        composable(route = AllFilesNavGraph.AudioFiles.name) {
            FilesTypeScreen(
                desc = R.string.modified_files_screen_audio_en,
                extensions = listOf("mp3"),
                navigateBefore = {
                    navController.popBackStack()
                    navController.navigate(AllFilesNavGraph.AllFiles.name)
                }
            )
        }
    }
}