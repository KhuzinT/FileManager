package com.example.filemanager.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.filemanager.screens.app.AppScreen
import com.example.filemanager.ui.theme.FileManagerTheme

@Composable
fun AppRoot() {
    val navController = rememberNavController()

    // Add theme changing later
    // Now dark theme always
    FileManagerTheme {
        AppScreen(navController = navController)
    }
}