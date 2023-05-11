package com.example.filemanager.screens.files

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FilesScreen(viewModel: FilesViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = uiState.value.files) {
            if (it.isDirectory) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { viewModel.processEvent(FilesEvent.OpenDirectory(it)) })) {
                    Text(text = "Directory")
                    
                    Text(text = it.name)
                }
            } else {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = {/*ToDo: открыть файл*/ })) {
                    Text(text = "File")
                    
                    Text(text = it.name)
                }
            }
        }
    }
}