package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.dao.TrainerDao
import com.rudra.titangymmanager.data.model.Trainer
import kotlinx.coroutines.flow.Flow

class TrainerRepositoryImpl(private val trainerDao: TrainerDao) : TrainerRepository {

    override suspend fun insertTrainer(trainer: Trainer) {
        trainerDao.insertTrainer(trainer)
    }

    override suspend fun updateTrainer(trainer: Trainer) {
        trainerDao.updateTrainer(trainer)
    }

    override suspend fun deleteTrainer(trainer: Trainer) {
        trainerDao.deleteTrainer(trainer)
    }

    override fun getAllTrainers(): Flow<List<Trainer>> {
        return trainerDao.getAllTrainers()
    }
}
