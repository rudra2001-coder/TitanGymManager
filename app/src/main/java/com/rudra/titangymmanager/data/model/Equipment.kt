package com.rudra.titangymmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "equipment")
data class Equipment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val purchaseDate: Long,
    val price: Double,
    val quantity: Int,
    val condition: EquipmentCondition
)
