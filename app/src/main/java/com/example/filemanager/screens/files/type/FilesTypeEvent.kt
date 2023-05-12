package com.example.filemanager.screens.files.type

import com.example.filemanager.screens.utils.SortedBy

sealed class FilesTypeEvent {
    object LoadFiles : FilesTypeEvent()
    data class SetExtensions(val extensions: List<String>) : FilesTypeEvent()
    data class UpdateSort(val sortedBy: SortedBy) : FilesTypeEvent()
}