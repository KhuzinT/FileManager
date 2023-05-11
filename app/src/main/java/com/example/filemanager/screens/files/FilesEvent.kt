package com.example.filemanager.screens.files

import java.io.File

sealed class FilesEvent() {
    data class OpenDirectory(val directory: File) : FilesEvent()
}