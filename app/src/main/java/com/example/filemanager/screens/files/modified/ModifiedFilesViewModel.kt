package com.example.filemanager.screens.files.modified

import android.os.Environment.getExternalStorageDirectory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filemanager.data.file.FileEntity
import com.example.filemanager.data.file.FileRepository
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

    }

    fun processEvent(event: ModifiedFilesEvent) {

    }

    private fun getFiles(directory: File): List<File> {
        return directory.listFiles()?.toList()?.sorted() ?: emptyList()
    }

}