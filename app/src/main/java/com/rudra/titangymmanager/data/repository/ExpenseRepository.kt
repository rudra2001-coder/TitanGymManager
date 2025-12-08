package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.model.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {

    suspend fun insertExpense(expense: Expense)

    suspend fun updateExpense(expense: Expense)

    suspend fun deleteExpense(expense: Expense)

    fun getAllExpenses(): Flow<List<Expense>>
}
