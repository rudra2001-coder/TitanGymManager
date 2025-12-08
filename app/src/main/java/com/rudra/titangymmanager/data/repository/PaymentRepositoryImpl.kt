package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.dao.PaymentDao
import com.rudra.titangymmanager.data.model.Payment
import kotlinx.coroutines.flow.Flow

class PaymentRepositoryImpl(private val paymentDao: PaymentDao) : PaymentRepository {

    override suspend fun insertPayment(payment: Payment) {
        paymentDao.insertPayment(payment)
    }

    override fun getPaymentsForMember(memberId: Long): Flow<List<Payment>> {
        return paymentDao.getPaymentsForMember(memberId)
    }

    override fun getAllPayments(): Flow<List<Payment>> {
        return paymentDao.getAllPayments()
    }
}
