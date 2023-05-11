package com.example.filemanager.data.file

import androidx.room.*

@Dao
interface FileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg file: FileEntity)

    @Delete
    suspend fun delete(fileEntity: FileEntity)

    @Query("DELETE FROM files WHERE directory = :path")
    suspend fun deleteByPath(path: String)

    @Query("SELECT * FROM files")
    fun getAll(): List<FileEntity>

    @Query("SELECT * FROM files WHERE directory = :path")
    fun getByPath(path: String) : List<FileEntity>
}