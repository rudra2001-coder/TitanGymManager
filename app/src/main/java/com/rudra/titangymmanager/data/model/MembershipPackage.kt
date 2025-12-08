package com.rudra.titangymmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "membership_packages")
data class MembershipPackage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val duration: Int, // in days
    val price: Double,
    val description: String? = null
)
