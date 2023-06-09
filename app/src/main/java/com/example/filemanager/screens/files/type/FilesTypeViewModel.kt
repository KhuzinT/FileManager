package com.example.filemanager.screens.files.type


import android.os.Environment.getExternalStorageDirectory
import androidx.lifecycle.ViewModel
import com.example.filemanager.screens.utils.SortedBy
import com.example.filemanager.screens.utils.getFiles
import com.example.filemanager.screens.utils.sortFiles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.io.File

class FilesTypeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FilesTypeState())
    val uiState = _uiState.asStateFlow()


    fun processEvent(event: FilesTypeEvent) {
        when (event) {
            is FilesTypeEvent.LoadFiles -> loadFiles()
            is FilesTypeEvent.SetExtensions -> setExtensions(event.extensions)
            is FilesTypeEvent.UpdateSort -> updateSort(event.sortedBy)
        }
    }

    private fun updateSort(sortedBy: SortedBy) {
        if (sortedBy != _uiState.value.sortedBy) {
            _uiState.update { currentState ->
                currentState.copy(
                    files = sortFiles(sortedBy, currentState.files),
                    sortedBy = sortedBy
                )
            }
        }
    }

    private fun setExtensions(extensions: List<String>) {
        _uiState.update { currentState ->
            currentState.copy(extensions = extensions)
        }
    }

    private fun loadFiles() {
        loadFilesRecursive(getExternalStorageDirectory())
    }

    private fun loadFilesRecursive(directory: File) {
        val files = getFiles(_uiState.value.sortedBy, directory)
        val typeFiles = mutableListOf<File>()
        for (file in files) {
            if (file.extension in _uiState.value.extensions) {
                typeFiles.add(file)
            }
        }
        typeFiles.addAll(_uiState.value.files)
        _uiState.update { currentState ->
            currentState.copy(files = typeFiles)
        }

        for (file in files) {
            if (file.isDirectory) {
                loadFilesRecursive(file)
            }
        }
    }
}