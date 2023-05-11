package com.example.filemanager.screens.files

import android.os.Environment.getExternalStorageDirectory
import java.io.File

data class FilesState(
    val directory: File = getExternalStorageDirectory(),
    val files: List<File> = emptyList(),
//    val modifiedFiles: List<File> = emptyList(),
)