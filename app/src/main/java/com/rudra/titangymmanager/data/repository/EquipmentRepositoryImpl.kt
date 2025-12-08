package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.dao.EquipmentDao
import com.rudra.titangymmanager.data.model.Equipment
import kotlinx.coroutines.flow.Flow

class EquipmentRepositoryImpl(private val equipmentDao: EquipmentDao) : EquipmentRepository {

    override suspend fun insertEquipment(equipment: Equipment) {
        equipmentDao.insertEquipment(equipment)
    }

    override suspend fun updateEquipment(equipment: Equipment) {
        equipmentDao.updateEquipment(equipment)
    }

    override suspend fun deleteEquipment(equipment: Equipment) {
        equipmentDao.deleteEquipment(equipment)
    }

    override fun getAllEquipment(): Flow<List<Equipment>> {
        return equipmentDao.getAllEquipment()
    }
}
