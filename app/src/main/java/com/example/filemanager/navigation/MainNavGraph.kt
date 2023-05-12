package com.example.filemanager.navigation

import androidx.annotation.StringRes
import com.example.filemanager.R

enum class MainNavGraph(@StringRes val title: Int) {
    Splash(title = R.string.splash_screen),
    Permission(title = R.string.permission_nav_host),
    Files(title = R.string.files_nav_host)
}
