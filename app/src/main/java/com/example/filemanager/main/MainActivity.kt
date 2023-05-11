package com.example.filemanager.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.filemanager.screens.files.FilesScreen
import com.example.filemanager.screens.permission.PermissionScreen
import com.example.filemanager.ui.theme.FileManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            FileManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    NavHost(navController = navController, startDestination = "permission") {
                        composable("permission") {
                            PermissionScreen(
                                navigateToFiles = {
                                    navController.navigate("files")
                                }
                            )
                        }
                        composable("files") {
                            FilesScreen()
                        }
                    }
                }
            }
        }
    }
}
