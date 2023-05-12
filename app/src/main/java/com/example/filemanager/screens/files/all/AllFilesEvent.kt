package com.example.filemanager.screens.files.all

import java.io.File

sealed class AllFilesEvent() {

    data class OpenDirectory(val absolutePath: String) : AllFilesEvent()
    data class SetDirectory(val absolutePath: String) : AllFilesEvent()
    object LoadFiles : AllFilesEvent()
}