package com.example.filemanager.screens.permission

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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

@Composable
fun PermissionScreen(
    navigateToFiles: () -> Unit,
//    context: Context,
    viewModel: PermissionViewModel = viewModel()
) {
    val context = LocalContext.current

    val uiState = viewModel.uiState.collectAsState()
    val checkAndNavigateToFiles = {
        viewModel.processEvent(PermissionEvent.CheckPermissions(context))
        if (uiState.value.isPermissionsReceived) {
            navigateToFiles()
        }
    }

    viewModel.processEvent(PermissionEvent.CheckPermissions(context))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.permission_screen_desc),
//            color = MaterialTheme.colors.onBackground
//            modifier = Modifier.size(300.dp, 70.dp)
            fontWeight = FontWeight(600),
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                checkAndNavigateToFiles()
                if (!uiState.value.isPermissionsReceived) {
                    Toast.makeText(
                        context,
                        "Пожалуйста, перейдите в настройки и предоставьте разрешение",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            modifier = Modifier.size(200.dp, 50.dp),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = stringResource(id = R.string.permission_screen_start),
                fontWeight = FontWeight(400),
                fontSize = 20.sp,
//                    color = MaterialTheme.colors
            )
        }


    }
}

