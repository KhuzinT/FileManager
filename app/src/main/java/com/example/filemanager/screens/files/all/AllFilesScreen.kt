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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.filemanager.R
import com.example.filemanager.screens.utils.*


@Composable
fun AllFilesScreen(navController: NavHostController, viewModel: AllFilesViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    InBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {

            if (uiState.value.directory.absolutePath == "/storage/emulated/0") {
                /*ToDo: раскомментировать, когда поймешь, как решить баг с дублированием файлов в FilesTypeScreen*/

//                FilesTypeNavigationBar(navController = navController, modifier = Modifier)
//
//                Spacer(modifier = Modifier.height(15.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = stringResource(id = R.string.all_files_screen_title),
                        modifier = Modifier
                            .height(45.dp)
                            .padding(horizontal = 15.dp),
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight(400),
                        fontSize = 32.sp
                    )


                    Spacer(modifier = Modifier.weight(1f))

                    FileSorter(
                        sortedBy = uiState.value.sortedBy,
                        updateSort = { sortedBy ->
                            viewModel.processEvent(AllFilesEvent.UpdateSort(sortedBy))
                        }
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier.clickable(onClick = {
                        val path = uiState.value.directory.absolutePath
                        val parent = path.substring(0, path.lastIndexOf('/'))
                        viewModel.processEvent(AllFilesEvent.OpenDirectory(parent))
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
                                .height(45.dp)
                                .widthIn(max = 250.dp),
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight(400),
                            fontSize = 32.sp,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    FileSorter(
                        sortedBy = uiState.value.sortedBy,
                        updateSort = { sortedBy ->
                            viewModel.processEvent(AllFilesEvent.UpdateSort(sortedBy))
                        }
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
                val context = LocalContext.current
                LazyColumn(
                    state = rememberLazyListState(initialFirstVisibleItemScrollOffset = 50),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items = uiState.value.files) {
                        FileCard(
                            file = it,
                            onActionClick = {
                                if (it.isDirectory) {
                                    viewModel.processEvent(AllFilesEvent.OpenDirectory(it.absolutePath))
                                } else {
                                    shareFile(it, context)
                                }
                            },
                            modifier = Modifier.clickable(
                                onClick = {
                                    if (it.isDirectory) {
                                        viewModel.processEvent(AllFilesEvent.OpenDirectory(it.absolutePath))
                                    } else {
                                        openFile(it, context)
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

