package com.example.filemanager.navigation.files

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.filemanager.navigation.MainNavGraph
import com.example.filemanager.navigation.files.all.AllFilesNavHost
import com.example.filemanager.navigation.files.modified.ModifiedFilesNavHost

@Composable
fun FilesNavHost(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen = try {
        FilesNavGraph.valueOf(backStackEntry?.destination?.route ?: FilesNavGraph.AllFilesHost.name)
    } catch (e: IllegalArgumentException) {
        FilesNavGraph.AllFilesHost
    }

    val topNavigationItems = listOf(
        FilesNavGraph.AllFilesHost,
        FilesNavGraph.ModifiedFilesHost
    )

    Scaffold(
        topBar = {
            if (topNavigationItems.contains(currentScreen)) {
                FilesTopNavigation(onSelected = { newScreen ->
                    navController.navigateSingleTopTo(newScreen.name)
                }, items = topNavigationItems, currentScreen = currentScreen)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            route = MainNavGraph.Files.name,
            startDestination = FilesNavGraph.AllFilesHost.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = FilesNavGraph.AllFilesHost.name) {
                AllFilesNavHost()
            }
            composable(route = FilesNavGraph.ModifiedFilesHost.name) {
                ModifiedFilesNavHost()
            }
        }
    }
}

@Composable
private fun FilesTopNavigation(
    onSelected: (FilesNavGraph) -> Unit,
    items: List<FilesNavGraph>,
    currentScreen: FilesNavGraph
) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = MaterialTheme.colors.background
    ) {
        val currentRoute = currentScreen.name
        items.forEach { screen ->
            BottomNavigationItem(
                selected = (currentRoute == screen.name),
                onClick = { onSelected(screen) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = screen.name,
                        modifier = Modifier.size(30.dp, 30.dp),
                        tint = if (screen.name == currentRoute) {
                            MaterialTheme.colors.primary.copy(alpha = 0.6f)
                        } else {
                            MaterialTheme.colors.onBackground.copy(alpha = 0.6f)
                        }
                    )
                }
            )
        }
    }
}


private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }