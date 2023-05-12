package com.example.filemanager.screens.files.all

import android.os.Environment.getExternalStorageDirectory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filemanager.screens.utils.SortedBy
import com.example.filemanager.screens.utils.getFiles
import com.example.filemanager.screens.utils.sortFiles
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
            is AllFilesEvent.UpdateSort -> updateSort(event.sortedBy)
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
//        viewModelScope.launch {}
        _uiState.update { currentState ->
            currentState.copy(files = getFiles(_uiState.value.sortedBy, currentState.directory))
        }

    }
}