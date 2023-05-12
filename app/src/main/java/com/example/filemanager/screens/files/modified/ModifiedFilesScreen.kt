package com.example.filemanager.screens.files.modified

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
import com.example.filemanager.R
import com.example.filemanager.screens.utils.FileCard
import com.example.filemanager.screens.utils.InBackground

@Composable
fun ModifiedFilesScreen(viewModel: ModifiedFilesViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()
    InBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {


            Text(
                text = stringResource(id = R.string.modified_files_screen_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .align(Alignment.Start),
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight(400),
                fontSize = 32.sp
            )


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
                LazyColumn(state = rememberLazyListState(), modifier = Modifier.fillMaxSize()) {
                    items(items = uiState.value.files) {
                        FileCard(
                            file = it,
                            modifier = Modifier.clickable(
                                onClick = {
                                    if (it.isDirectory) {
                                        /*ToDo: придумать что-то*/
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