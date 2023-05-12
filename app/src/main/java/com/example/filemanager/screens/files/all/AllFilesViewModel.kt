package com.example.filemanager.screens.files.all

import android.os.Environment.getExternalStorageDirectory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filemanager.screens.utils.getFiles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class AllFilesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AllFilesState())
    val uiState = _uiState.asStateFlow()

    init {
        openDirectory(getExternalStorageDirectory().absolutePath)
    }

    fun processEvent(event: AllFilesEvent) {
        when (event) {
            is AllFilesEvent.OpenDirectory -> openDirectory(event.absolutePath)
            is AllFilesEvent.LoadFiles -> loadFiles()
            is AllFilesEvent.SetDirectory -> setDirectory(event.absolutePath)
        }
    }

    private fun openDirectory(absolutePath: String) {
        setDirectory(absolutePath)
        loadFiles()
    }

    private fun setDirectory(absolutePath: String) {
        val directory = File(absolutePath)
        _uiState.update { currentState ->
            currentState.copy(directory = directory)
        }
    }

    private fun loadFiles() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(files = getFiles(currentState.directory))
            }
        }
    }
}