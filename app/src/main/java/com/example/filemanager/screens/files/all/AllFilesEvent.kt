package com.example.filemanager.screens.files.all

import com.example.filemanager.screens.utils.SortedBy

sealed class AllFilesEvent() {

    data class OpenDirectory(val absolutePath: String) : AllFilesEvent()
    data class SetDirectory(val absolutePath: String) : AllFilesEvent()
    data class UpdateSort(val sortedBy: SortedBy) : AllFilesEvent()
    object LoadFiles : AllFilesEvent()
}