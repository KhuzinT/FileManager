package com.example.filemanager.navigation.files

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.FolderCopy
import androidx.compose.material.icons.outlined.FolderSpecial
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.filemanager.R

enum class FilesNavGraph(@StringRes val title: Int, val icon: ImageVector = Icons.Outlined.Folder) {
    AllFilesHost(title = R.string.all_files_nav_host, icon = Icons.Outlined.FolderCopy),
    ModifiedFilesHost(title = R.string.modified_files_nav_host, icon = Icons.Outlined.FolderSpecial)
}