package com.example.filemanager.screens.files.modified

import com.example.filemanager.screens.files.all.AllFilesEvent
import com.example.filemanager.screens.utils.SortedBy

sealed class ModifiedFilesEvent() {
    data class UpdateSort(val sortedBy: SortedBy) : ModifiedFilesEvent()
}