package com.example.filemanager.screens.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.filemanager.screens.app.AppEvent
import com.example.filemanager.screens.app.AppViewModel
import com.example.filemanager.screens.utils.InBackground
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToPermission: () -> Unit,
    navigateToMain: () -> Unit,
    appViewModel: AppViewModel
) {
    val uiState = appViewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = uiState) {
        appViewModel.processEvent(AppEvent.CheckPermissions(context = context))
        delay(2000)
        if (uiState.value.allPermissionsReceived) {
            navigateToMain()
        } else {
            navigateToPermission()
        }
    }

    InBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Save,
                contentDescription = null,
                modifier = Modifier.size(185.dp, 185.dp),
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}