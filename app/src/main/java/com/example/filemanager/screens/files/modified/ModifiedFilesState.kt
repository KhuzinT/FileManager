package com.example.filemanager.screens.files.modified

import java.io.File

data class ModifiedFilesState(
    val files: List<File> = emptyList()
)