package com.example.filemanager.screens.files.type

import java.io.File

sealed class FilesTypeEvent {
    data class LoadFiles(val directory: File) : FilesTypeEvent()
    data class SetExtensions(val extensions: List<String>) : FilesTypeEvent()
}