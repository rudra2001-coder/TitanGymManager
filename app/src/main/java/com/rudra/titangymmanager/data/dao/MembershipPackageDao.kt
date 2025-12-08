package com.rudra.titangymmanager.data.dao

import androidx.room.*
import com.rudra.titangymmanager.data.model.MembershipPackage
import kotlinx.coroutines.flow.Flow

@Dao
interface MembershipPackageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPackage(membershipPackage: MembershipPackage)

    @Update
    suspend fun updatePackage(membershipPackage: MembershipPackage)

    @Delete
    suspend fun deletePackage(membershipPackage: MembershipPackage)

    @Query("SELECT * FROM membership_packages ORDER BY name ASC")
    fun getAllPackages(): Flow<List<MembershipPackage>>
}
