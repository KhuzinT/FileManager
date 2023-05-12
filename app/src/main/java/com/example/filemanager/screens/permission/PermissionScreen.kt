package com.example.filemanager.screens.permission

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.filemanager.R
import com.example.filemanager.screens.utils.InBackground

@Composable
fun PermissionScreen(
    navigateToSplash: () -> Unit,
    viewModel: PermissionViewModel = viewModel()
) {
    val context = LocalContext.current

    InBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.permission_screen_desc),
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight(400),
                fontSize = 20.sp,
                modifier = Modifier.size(300.dp, 100.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    viewModel.processEvent(PermissionEvent.OpenSettings(context))
                    navigateToSplash()
                },
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = stringResource(id = R.string.permission_screen_start),
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight(600),
                    fontSize = 20.sp
                )
            }
        }
    }
}

