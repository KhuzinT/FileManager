package com.example.filemanager.screens.utils

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.filemanager.R
import com.example.filemanager.navigation.files.all.AllFilesNavGraph
import com.example.filemanager.navigation.files.modified.ModifiedFilesNavGraph


@Composable
fun FilesTypeNavigationBar(navController: NavHostController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FileTypeNavigationItem(
            desc = R.string.modified_files_screen_images_ru,
            icon = Icons.Outlined.Image,
            background = Color.Green.copy(alpha = 0.5f),
            onClick = { navController.navigate(AllFilesNavGraph.ImagesFiles.name) },
            modifier = modifier
        )

        FileTypeNavigationItem(
            desc = R.string.modified_files_screen_movies_ru,
            icon = Icons.Outlined.Movie,
            background = Color.Yellow.copy(alpha = 0.5f),
            onClick = { navController.navigate(AllFilesNavGraph.MoviesFiles.name) },
            modifier = modifier
        )

        FileTypeNavigationItem(
            desc = R.string.modified_files_screen_docs_ru,
            icon = Icons.Outlined.Description,
            background = Color.Magenta.copy(alpha = 0.5f),
            onClick = { navController.navigate(AllFilesNavGraph.DocsFiles.name) },
            modifier = modifier
        )

        FileTypeNavigationItem(
            desc = R.string.modified_files_screen_audio_ru,
            icon = Icons.Outlined.Headphones,
            background = Color.Red.copy(alpha = 0.5f),
            onClick = { navController.navigate(AllFilesNavGraph.AudioFiles.name) },
            modifier = modifier
        )
    }
}

@Composable
fun FileTypeNavigationItem(
    @StringRes desc: Int,
    icon: ImageVector,
    background: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .size(80.dp, 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = onClick,
            modifier = modifier
                .padding(10.dp)
                .size(50.dp, 50.dp),
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.outlinedButtonColors(backgroundColor = background)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground,
                modifier = modifier.size(35.dp, 35.dp)
            )
        }

        Text(
            text = stringResource(id = desc),
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight(400),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}