package com.rudra.titangymmanager.data.dao

import androidx.room.*
import com.rudra.titangymmanager.data.model.Trainer
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrainer(trainer: Trainer)

    @Update
    suspend fun updateTrainer(trainer: Trainer)

    @Delete
    suspend fun deleteTrainer(trainer: Trainer)

    @Query("SELECT * FROM trainers ORDER BY name ASC")
    fun getAllTrainers(): Flow<List<Trainer>>
}
