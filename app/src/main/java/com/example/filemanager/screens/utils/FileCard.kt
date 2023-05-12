package com.example.filemanager.screens.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Composable
fun FileCard(file: File, onActionClick: () -> Unit, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(10.dp))

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = if (file.isDirectory) {
                Icons.Outlined.Folder
            } else {
                when (file.extension.lowercase()) {
                    in listOf("jpeg", "png", "jpg", "img") -> {
                        Icons.Outlined.Image
                    }
                    "mp3" -> {
                        Icons.Outlined.Headphones
                    }
                    "mp4" -> {
                        Icons.Outlined.Movie
                    }
                    "pdf" -> {
                        Icons.Outlined.PictureAsPdf
                    }
                    "gif" -> {
                        Icons.Outlined.GifBox
                    }
                    else -> {
                        Icons.Outlined.Description
                    }
                }
            },
            contentDescription = file.name,
            tint = MaterialTheme.colors.primary,
            modifier = modifier.size(50.dp, 50.dp)
        )

        Spacer(modifier = modifier.width(5.dp))

        Column() {
            val maxLen = 20
            val name = if (file.name.length > maxLen) {
                file.name.substring(0, maxLen)
            } else {
                file.name
            }

            Text(
                text = name,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight(400),
                fontSize = 20.sp,
                modifier = modifier
            )

            Row {
                if (!file.isDirectory) {
                    val size = (file.length().toDouble() / 1000000.0).toString() + " MB"
                    Text(
                        text = size, color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
                        fontWeight = FontWeight(300),
                        fontSize = 14.sp,
                        modifier = modifier
                    )

                    Spacer(modifier = modifier.width(10.dp))
                }

                val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
                Text(
                    text = dateFormat.format(file.lastModified()).toString(),
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
                    fontWeight = FontWeight(300),
                    fontSize = 14.sp,
                    modifier = modifier
                )
            }
        }

        Spacer(modifier = modifier.weight(1f))

        Icon(
            imageVector = if (file.isDirectory) {
                Icons.Outlined.NavigateNext
            } else {
                Icons.Outlined.Share
            },
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
            modifier = modifier.size(40.dp, 40.dp).clickable(onClick = onActionClick)
        )
    }

    Spacer(modifier = modifier.height(10.dp))

    Divider(color = MaterialTheme.colors.onBackground, thickness = 1.dp)
}