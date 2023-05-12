package com.example.filemanager.screens.files.type

import com.example.filemanager.screens.utils.SortedBy
import java.io.File

data class FilesTypeState(
    val extensions : List<String> = emptyList(),
    val files: List<File> = emptyList(),
    val sortedBy: SortedBy = SortedBy.NameAZ
)