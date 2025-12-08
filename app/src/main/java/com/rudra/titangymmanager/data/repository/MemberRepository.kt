package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.data.model.MonthlyNewMembers
import kotlinx.coroutines.flow.Flow

interface MemberRepository {

    suspend fun insertMember(member: Member)

    suspend fun updateMember(member: Member)

    suspend fun deleteMember(member: Member)

    fun getAllMembers(): Flow<List<Member>>

    fun getMemberById(memberId: Long): Flow<Member?>

    fun searchMembers(query: String): Flow<List<Member>>

    fun getActiveMembers(): Flow<List<Member>>

    fun getExpiredMembers(): Flow<List<Member>>

    fun getDuePaymentMembers(): Flow<List<Member>>

    fun getUpcomingExpiryMembers(): Flow<List<Member>>

    fun getMonthlyNewMembers(): Flow<List<MonthlyNewMembers>>

    fun getMembersExpiringBetween(startDate: Long, endDate: Long): Flow<List<Member>>
}
