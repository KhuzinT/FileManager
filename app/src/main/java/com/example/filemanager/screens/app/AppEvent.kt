package com.example.filemanager.screens.app

import android.content.Context

sealed class AppEvent {
    data class CheckPermissions(val context: Context) : AppEvent()
}