package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.model.Trainer
import kotlinx.coroutines.flow.Flow

interface TrainerRepository {

    suspend fun insertTrainer(trainer: Trainer)

    suspend fun updateTrainer(trainer: Trainer)

    suspend fun deleteTrainer(trainer: Trainer)

    fun getAllTrainers(): Flow<List<Trainer>>
}
