package com.example.filemanager.data.file

import javax.inject.Inject

class FileRepository
@Inject constructor(
    private val fileDao: FileDao
) {
    suspend fun insert(file: FileEntity) = fileDao.insert(file)

    suspend fun delete(file: FileEntity) = fileDao.delete(file)

    suspend fun deleteByPath(path: String) = fileDao.deleteByPath(path)

    fun getAll(): List<FileEntity> = fileDao.getAll()

    fun getByPath(path: String) = fileDao.getByPath(path)
}