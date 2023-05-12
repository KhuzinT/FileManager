package com.example.filemanager.screens.files.modified

import com.example.filemanager.screens.utils.SortedBy
import java.io.File

data class ModifiedFilesState(
    val files: List<File> = emptyList(),
    val sortedBy: SortedBy = SortedBy.NameAZ
)