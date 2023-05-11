package com.example.filemanager.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.filemanager.data.file.FileDao
import com.example.filemanager.data.file.FileEntity

@Database(entities = [FileEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fileDao() : FileDao
}