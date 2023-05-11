package com.example.filemanager.data.file

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "files")
data class FileEntity(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "directory") val directory: String,
    @ColumnInfo(name = "hash") val hash: String
) : Parcelable