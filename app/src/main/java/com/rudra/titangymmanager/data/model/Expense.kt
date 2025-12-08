package com.rudra.titangymmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val category: ExpenseCategory,
    val amount: Double,
    val date: Long,
    val description: String,
    val receiptPhotoPath: String? = null
)
