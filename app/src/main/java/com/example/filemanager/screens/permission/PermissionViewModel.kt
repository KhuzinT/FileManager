package com.example.filemanager.screens.permission

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel

class PermissionViewModel : ViewModel() {
    fun processEvent(event: PermissionEvent) {
        when (event) {
            is PermissionEvent.OpenSettings -> openSettings(event.context)
        }
    }

    private fun openSettings(context: Context) {
        val intent = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        } else {
            Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(context, intent, null)
    }

}