package com.example.filemanager.screens.permission

import android.content.Context
import com.example.filemanager.screens.app.AppEvent

sealed class PermissionEvent {
    data class OpenSettings(val context: Context) : PermissionEvent()
}