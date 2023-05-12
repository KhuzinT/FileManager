package com.example.filemanager.navigation.files.all

import androidx.annotation.StringRes
import com.example.filemanager.R

enum class AllFilesNavGraph(@StringRes val title: Int) {
    AllFiles(title = R.string.all_files_screen),
    ImagesFiles(title = R.string.images_files_screen),
    MoviesFiles(title = R.string.movies_files_screen),
    DocsFiles(title = R.string.docs_files_screen),
    AudioFiles(title = R.string.audio_files_screen)
}