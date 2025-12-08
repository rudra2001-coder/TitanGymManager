package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.model.Equipment
import kotlinx.coroutines.flow.Flow

interface EquipmentRepository {

    suspend fun insertEquipment(equipment: Equipment)

    suspend fun updateEquipment(equipment: Equipment)

    suspend fun deleteEquipment(equipment: Equipment)

    fun getAllEquipment(): Flow<List<Equipment>>
}
