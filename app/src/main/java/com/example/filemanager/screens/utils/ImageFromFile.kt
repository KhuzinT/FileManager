package com.example.filemanager.screens.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File

@Composable
fun ImageFromFile(file: File, modifier: Modifier = Modifier) {
    val bitmap = loadBitmap(file)
    if (bitmap != null) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = file.name,
            modifier = modifier
        )
    } else {
        Icon(
            imageVector = Icons.Outlined.Image,
            contentDescription = file.name,
            tint = MaterialTheme.colors.primary,
            modifier = modifier
        )
    }
}

private fun loadBitmap(file: File): Bitmap? {
    val options = BitmapFactory.Options()
    options.inPreferredConfig = Bitmap.Config.ARGB_8888
    return try {
        BitmapFactory.decodeFile(file.absolutePath, options)
    } catch (e: Exception) {
        null
    }
}