package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.model.MembershipPackage
import kotlinx.coroutines.flow.Flow

interface MembershipPackageRepository {

    suspend fun insertPackage(membershipPackage: MembershipPackage)

    suspend fun updatePackage(membershipPackage: MembershipPackage)

    suspend fun deletePackage(membershipPackage: MembershipPackage)

    fun getAllPackages(): Flow<List<MembershipPackage>>
}
