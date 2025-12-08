package com.rudra.titangymmanager.data.dao

import androidx.room.*
import com.rudra.titangymmanager.data.model.Payment
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPayment(payment: Payment)

    @Query("SELECT * FROM payments WHERE memberId = :memberId ORDER BY date DESC")
    fun getPaymentsForMember(memberId: Long): Flow<List<Payment>>

    @Query("SELECT * FROM payments ORDER BY date DESC")
    fun getAllPayments(): Flow<List<Payment>>
}
