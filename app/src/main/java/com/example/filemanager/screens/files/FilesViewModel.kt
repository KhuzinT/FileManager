package com.example.filemanager.screens.files

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filemanager.data.file.FileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FilesViewModel @Inject constructor(
    private val repository: FileRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(FilesState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(files = getFiles(currentState.directory))
            }
        }
    }

    fun processEvent(event: FilesEvent) {
        when (event) {
            is FilesEvent.OpenDirectory -> openDirectory(event.directory)
        }
    }

    private fun openDirectory(directory: File) {
        _uiState.update { currentState ->
            currentState.copy(files = getFiles(directory), directory = directory)

        }
    }

    private fun getFiles(directory: File) : List<File> {
        return directory.listFiles()?.toList()?.sorted() ?: emptyList()
    }
}