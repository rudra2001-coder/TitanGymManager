package com.rudra.titangymmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members")
data class Member(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val phoneNumber: String,
    val gender: String,
    val age: Int,
    val weight: Float? = null,
    val height: Float? = null,
    val address: String? = null,
    val joiningDate: Long,
    val profilePhotoPath: String? = null,
    val membershipType: String,
    val membershipStartDate: Long,
    val membershipEndDate: Long,
    val feesPaid: Double,
    val dueAmount: Double,
    val paymentMethod: String,
    val registrationDate: Long? = null,
    val lastRenewalDate: Long? = null,
    val paymentAmount: Double? = null,
    val paymentDate: Long? = null,
    val lastPaymentDate: Long? = null,
    val lastPaymentAmount: Double? = null,
    val lastPaymentMode: String? = null,

    // Add other fields as needed

)
