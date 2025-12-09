package com.rudra.titangymmanager.data.dao

import androidx.room.*
import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.data.model.MonthlyIncome
import com.rudra.titangymmanager.data.model.MonthlyNewMembers
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMember(member: Member)

    @Update
    suspend fun updateMember(member: Member)

    @Delete
    suspend fun deleteMember(member: Member)

    @Query("SELECT * FROM members ORDER BY name ASC")
    fun getAllMembers(): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE id = :memberId")
    fun getMemberById(memberId: Long): Flow<Member?>

    @Query("SELECT * FROM members WHERE name LIKE '%' || :query || '%' OR phoneNumber LIKE '%' || :query || '%'" )
    fun searchMembers(query: String): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE membershipEndDate >= :currentDate ORDER BY name ASC")
    fun getActiveMembers(currentDate: Long): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE membershipEndDate < :currentDate ORDER BY name ASC")
    fun getExpiredMembers(currentDate: Long): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE dueAmount > 0 ORDER BY name ASC")
    fun getDuePaymentMembers(): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE membershipEndDate BETWEEN :startDate AND :endDate ORDER BY membershipEndDate ASC")
    fun getUpcomingExpiryMembers(startDate: Long, endDate: Long): Flow<List<Member>>

    @Query("SELECT strftime('%Y-%m', joiningDate / 1000, 'unixepoch') as month, COUNT(*) as count FROM members GROUP BY month ORDER BY month ASC")
    fun getMonthlyNewMembers(): Flow<List<MonthlyNewMembers>>

    @Query("SELECT * FROM members WHERE membershipEndDate BETWEEN :startDate AND :endDate ORDER BY membershipEndDate ASC")
    fun getMembersExpiringBetween(startDate: Long, endDate: Long): Flow<List<Member>>
    
    @Query("SELECT * FROM members WHERE registrationDate BETWEEN :startDate AND :endDate")
    fun getMembersByRegistrationDate(startDate: Long, endDate: Long): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE lastRenewalDate BETWEEN :startDate AND :endDate")
    fun getMembersByRenewalDate(startDate: Long, endDate: Long): Flow<List<Member>>

    @Query("SELECT COALESCE(SUM(paymentAmount), 0) FROM members WHERE paymentDate BETWEEN :startDate AND :endDate")
    fun getTotalIncomeBetweenDates(startDate: Long, endDate: Long): Flow<Double>

    @Query("""
        SELECT 
            strftime('%Y-%m', datetime(paymentDate/1000, 'unixepoch')) as month,
            COALESCE(SUM(paymentAmount), 0) as income
        FROM members 
        WHERE paymentDate IS NOT NULL 
        GROUP BY strftime('%Y-%m', datetime(paymentDate/1000, 'unixepoch'))
        ORDER BY paymentDate DESC 
        LIMIT 6
    """)
    fun getMonthlyIncomeData(): Flow<List<MonthlyIncome>>

    @Query("SELECT * FROM members WHERE membershipType = :membershipType")
    fun getMembersByMembershipType(membershipType: String): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE membershipType = :membershipType AND membershipEndDate >= :currentDate")
    fun getActiveMembersByMembershipType(membershipType: String, currentDate: Long): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE membershipType = :membershipType AND membershipEndDate < :currentDate")
    fun getExpiredMembersByMembershipType(membershipType: String, currentDate: Long): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE membershipType = :membershipType AND dueAmount > 0")
    fun getDuePaymentMembersByMembershipType(membershipType: String): Flow<List<Member>>
}
