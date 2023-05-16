package com.example.filemanager.screens.utils

import android.content.Context
import android.content.Intent
import android.webkit.MimeTypeMap
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File

fun getFiles(sortedBy: SortedBy, directory: File): List<File> {
    return sortFiles(sortedBy, directory.listFiles()?.toList() ?: emptyList())
}

fun openFile(file: File, context: Context) {
    if (file.extension in listOf("png", "jpeg", "jpg", "img")) {
        val uri = FileProvider.getUriForFile(context, "com.example.filemanager.main.provider", file)

        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val mimeType = MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()))
            ?: "image/*"
        intent.setDataAndType(uri, mimeType)

        context.startActivity(intent)
    } else {
        val uri = FileProvider.getUriForFile(context, "com.example.filemanager.main.provider", file)

        val intent = Intent(Intent.ACTION_VIEW)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val mimeType = MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()))
            ?: "application/*"
        intent.setDataAndType(uri, mimeType)

        val chooserIntent = Intent.createChooser(intent, "Открыть с помощью")

        ContextCompat.startActivity(context, chooserIntent, null)
    }
}

fun shareFile(file: File, context: Context) {
    val uri = FileProvider.getUriForFile(context, "com.example.filemanager.main.provider", file)

    val intent = Intent(Intent.ACTION_SEND)
    intent.putExtra(Intent.EXTRA_STREAM, uri)

    val mimeType = MimeTypeMap.getSingleton()
        .getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()))
        ?: "application/*"
    intent.type = mimeType

    val chooserIntent = Intent.createChooser(intent, "Поделиться")
    chooserIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    ContextCompat.startActivity(context, chooserIntent, null)
}


fun sortFiles(sortedBy: SortedBy, list: List<File>): List<File> {
    return when (sortedBy) {
        SortedBy.NameAZ -> {
            list.sortedBy { it.name }.reversed()
        }
        SortedBy.NameZA -> {
            list.sortedBy { it.name }
        }
        SortedBy.SizeIncrease -> {
            list.sortedBy { it.length() }
        }
        SortedBy.SizeDecrease -> {
            list.sortedBy { it.length() }.reversed()
        }
        SortedBy.DateIncrease -> {
            list.sortedBy { it.lastModified() }.reversed()
        }
        SortedBy.DateDecrease -> {
            list.sortedBy { it.lastModified() }
        }
        SortedBy.Extension -> {
            list.sortedBy { it.extension }
        }
    }
}
