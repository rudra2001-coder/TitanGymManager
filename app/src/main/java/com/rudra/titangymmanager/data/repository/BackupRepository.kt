package com.rudra.titangymmanager.data.repository

import android.content.Context
import android.net.Uri
import com.rudra.titangymmanager.data.AppDatabase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

interface BackupRepository {
    suspend fun backupDatabase(uri: Uri): Boolean
    suspend fun restoreDatabase(uri: Uri): Boolean
}

@Singleton
class BackupRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val db: AppDatabase
) : BackupRepository {

    private val dbName = "titan_gym_manager_db"

    override suspend fun backupDatabase(uri: Uri): Boolean {
        return withContext(Dispatchers.IO) {
            val currentDb = context.getDatabasePath(dbName)
            if (!currentDb.exists()) {
                return@withContext false
            }
            try {
                context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                    currentDb.inputStream().use { inputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    override suspend fun restoreDatabase(uri: Uri): Boolean {
        return withContext(Dispatchers.IO) {
            val currentDb = context.getDatabasePath(dbName)
            val walFile = File(currentDb.parent, "$dbName-wal")
            val shmFile = File(currentDb.parent, "$dbName-shm")

            db.close()

            try {
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    currentDb.outputStream().use { outputStream ->
                        inputStream.copyTo(outputStream)
                    }
                }
                if (walFile.exists()) walFile.delete()
                if (shmFile.exists()) shmFile.delete()
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}
