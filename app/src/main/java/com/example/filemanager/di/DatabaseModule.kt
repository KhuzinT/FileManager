package com.example.filemanager.di

import android.content.Context
import androidx.room.Room
import com.example.filemanager.data.db.AppDatabase
import com.example.filemanager.data.file.FileDao
import com.example.filemanager.data.file.FileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "files_db"
    ).build()

    @Provides
    fun provideFileDao(appDatabase: AppDatabase) : FileDao = appDatabase.fileDao()

    @Provides
    fun provideFileRepository(dao: FileDao) : FileRepository = FileRepository(dao)
}