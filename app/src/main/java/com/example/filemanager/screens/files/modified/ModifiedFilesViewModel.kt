package com.example.filemanager.screens.files.modified

import android.os.Environment.getExternalStorageDirectory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filemanager.data.file.FileEntity
import com.example.filemanager.data.file.FileRepository
import com.example.filemanager.screens.utils.SortedBy
import com.example.filemanager.screens.utils.getFiles
import com.example.filemanager.screens.utils.sortFiles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ModifiedFilesViewModel
@Inject constructor(private val repository: FileRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(ModifiedFilesState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            compareHashesRecursive(getExternalStorageDirectory())
        }
    }

    fun processEvent(event: ModifiedFilesEvent) {
        when(event) {
            is ModifiedFilesEvent.UpdateSort -> updateSort(event.sortedBy)
        }
    }

    private fun updateSort(sortedBy: SortedBy) {
        if (sortedBy != _uiState.value.sortedBy) {
            _uiState.update { currentState ->
                currentState.copy(files = sortFiles(sortedBy, currentState.files), sortedBy = sortedBy)
            }
        }
    }

    private suspend fun compareHashesRecursive(directory: File) {
        val files = getFiles(_uiState.value.sortedBy, directory)
        val saved = repository.getByPath(directory.absolutePath)
        val modified = mutableListOf<File>()
        for (file in files) {
            val entity = FileEntity(
                name = file.absolutePath,
                directory = file.parentFile?.absolutePath ?: "",
                hash = file.hashCode().toString()
            )
            if (entity !in saved) {
                modified.add(file)
            }
        }

        modified.addAll(_uiState.value.files)

        _uiState.update { currentState ->
            currentState.copy(files = modified)
        }

        repository.deleteByPath(directory.absolutePath)
        for (file in files) {
            val entity = FileEntity(
                name = file.absolutePath,
                directory = file.parentFile?.absolutePath ?: "",
                hash = file.hashCode().toString()
            )
            repository.insert(entity)
        }

        for (file in files) {
            if (file.isDirectory) {
                compareHashesRecursive(file)
            }
        }
    }

}