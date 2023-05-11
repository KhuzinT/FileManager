package com.example.filemanager.screens.permission

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

class PermissionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PermissionState())
    val uiState = _uiState.asStateFlow()

    fun processEvent(event: PermissionEvent) {
        when (event) {
            is PermissionEvent.CheckPermissions -> checkPermission(event.context)
        }
    }

    private fun checkPermission(context: Context) {
        var isPermissionReceived = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            isPermissionReceived = Environment.isExternalStorageManager()
        } else {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                isPermissionReceived = false
            }
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                isPermissionReceived = false
            }
        }

        _uiState.update { currentState ->
            currentState.copy(isPermissionsReceived = isPermissionReceived)
        }
    }
}