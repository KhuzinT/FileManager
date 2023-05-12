package com.example.filemanager.screens.files.all

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NavigateBefore
import androidx.compose.material.icons.outlined.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.filemanager.R
import com.example.filemanager.screens.utils.FileCard
import com.example.filemanager.screens.utils.InBackground

/*ToDo: добавить сортировку*/

@Composable
fun AllFilesScreen(viewModel: AllFilesViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    InBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {

            if (uiState.value.directory.absolutePath == "/storage/emulated/0") {
                Text(
                    text = stringResource(id = R.string.all_files_screen_title),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .align(Alignment.Start)
                        .padding(horizontal = 15.dp),
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight(400),
                    fontSize = 32.sp
                )
            } else {
                Row(modifier = Modifier.clickable(onClick = {
                    val path = uiState.value.directory.absolutePath
                    val parent = path.substring(0, path.lastIndexOf('/'))
                    viewModel.processEvent(AllFilesEvent.SetDirectory(parent))
                    viewModel.processEvent(AllFilesEvent.LoadFiles)
                }), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.NavigateBefore,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .size(40.dp, 40.dp)
                    )

                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = uiState.value.directory.name,
                        modifier = Modifier
                            .height(45.dp),
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight(400),
                        fontSize = 32.sp
                    )
                }
            }

            Divider(color = MaterialTheme.colors.onBackground, thickness = 1.dp)

            if (uiState.value.files.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Save,
                        contentDescription = null,
                        modifier = Modifier.size(185.dp, 185.dp),
                        tint = MaterialTheme.colors.onBackground
                    )

                    Text(
                        text = stringResource(id = R.string.all_files_screen_empty),
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight(300),
                        fontSize = 28.sp
                    )
                }
            } else {
                LazyColumn(
                    state = rememberLazyListState(initialFirstVisibleItemScrollOffset = 50),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items = uiState.value.files) {
                        FileCard(
                            file = it,
                            modifier = Modifier.clickable(
                                onClick = {
                                    if (it.isDirectory) {
                                        viewModel.processEvent(AllFilesEvent.SetDirectory(it.absolutePath))
                                        viewModel.processEvent(AllFilesEvent.LoadFiles)
                                    } else {
                                        /*ToDo: открыть файл*/
                                    }
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}

