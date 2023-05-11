package com.example.filemanager.screens.permission

import android.content.Context

sealed class PermissionEvent {
    data class CheckPermissions(val context: Context) : PermissionEvent()
}