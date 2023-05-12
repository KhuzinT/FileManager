package com.example.filemanager.screens.files.all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filemanager.data.file.FileEntity
import com.example.filemanager.data.file.FileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AllFilesViewModel @Inject constructor(private val repository: FileRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(AllFilesState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadFiles()
            saveFilesRecursive(_uiState.value.directory)
        }
    }

    fun processEvent(event: AllFilesEvent) {
        when (event) {
            is AllFilesEvent.LoadFiles -> loadFiles()
            is AllFilesEvent.SetDirectory -> setDirectory(event.absolutePath)
        }
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

    private fun getFiles(directory: File): List<File> {
        return directory.listFiles()?.toList()?.sorted() ?: emptyList()
    }

    private suspend fun saveFilesRecursive(directory: File) {
        repository.deleteByPath(directory.absolutePath)
        val files = _uiState.value.files
        for (file in files) {
            repository.insert(
                FileEntity(
                    name = file.absolutePath,
                    directory = file.parentFile?.absolutePath ?: "",
                    hash = file.hashCode().toString()
                )
            )
        }
        for (file in files) {
            if (file.isDirectory) {
                saveFilesRecursive(file)
            }
        }
    }
}