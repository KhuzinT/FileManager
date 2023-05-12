package com.example.filemanager.screens.files.type

import com.example.filemanager.screens.files.modified.ModifiedFilesEvent
import com.example.filemanager.screens.utils.SortedBy
import java.io.File

sealed class FilesTypeEvent {
    data class LoadFiles(val directory: File) : FilesTypeEvent()
    data class SetExtensions(val extensions: List<String>) : FilesTypeEvent()
    data class UpdateSort(val sortedBy: SortedBy) : FilesTypeEvent()
}