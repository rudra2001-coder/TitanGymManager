package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.dao.MembershipPackageDao
import com.rudra.titangymmanager.data.model.MembershipPackage
import kotlinx.coroutines.flow.Flow

class MembershipPackageRepositoryImpl(private val membershipPackageDao: MembershipPackageDao) : MembershipPackageRepository {

    override suspend fun insertPackage(membershipPackage: MembershipPackage) {
        membershipPackageDao.insertPackage(membershipPackage)
    }

    override suspend fun updatePackage(membershipPackage: MembershipPackage) {
        membershipPackageDao.updatePackage(membershipPackage)
    }

    override suspend fun deletePackage(membershipPackage: MembershipPackage) {
        membershipPackageDao.deletePackage(membershipPackage)
    }

    override fun getAllPackages(): Flow<List<MembershipPackage>> {
        return membershipPackageDao.getAllPackages()
    }
}
