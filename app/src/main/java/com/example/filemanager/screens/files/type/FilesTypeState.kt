package com.example.filemanager.screens.files.type

import java.io.File

data class FilesTypeState(
    val extensions : List<String> = emptyList(),
    val files: List<File> = emptyList()
)