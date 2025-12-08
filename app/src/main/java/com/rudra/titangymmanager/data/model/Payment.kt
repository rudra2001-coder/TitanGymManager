package com.rudra.titangymmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "payments")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val memberId: Long,
    val amount: Double,
    val date: Long,
    val paymentMethod: String // Cash, bKash, Nagad, Bank
)
