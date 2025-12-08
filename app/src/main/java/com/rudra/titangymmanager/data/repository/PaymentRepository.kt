package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.model.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {

    suspend fun insertPayment(payment: Payment)

    fun getPaymentsForMember(memberId: Long): Flow<List<Payment>>

    fun getAllPayments(): Flow<List<Payment>>
}
