package com.example.filemanager.screens.files.all

import android.os.Environment.getExternalStorageDirectory
import java.io.File

data class AllFilesState(
    val directory: File = getExternalStorageDirectory(),
    val files: List<File> = emptyList()
)