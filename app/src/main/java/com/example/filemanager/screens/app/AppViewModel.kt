package com.example.filemanager.screens.app

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppState())
    val uiState = _uiState.asStateFlow()

    fun processEvent(event: AppEvent) {
        when (event) {
            is AppEvent.CheckPermissions -> checkPermission(event.context)
        }
    }

    private fun checkPermission(context: Context) {
        var allPermissionReceived = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            allPermissionReceived = Environment.isExternalStorageManager()
        } else {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                allPermissionReceived = false
            }

            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                allPermissionReceived = false
            }
        }

        _uiState.update { currentState ->
            currentState.copy(allPermissionsReceived = allPermissionReceived)
        }
    }
}