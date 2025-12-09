package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.dao.MemberDao
import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.data.model.MonthlyIncome
import com.rudra.titangymmanager.data.model.MonthlyNewMembers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject

class MemberRepositoryImpl @Inject constructor(
    private val memberDao: MemberDao
) : MemberRepository {

    override suspend fun insertMember(member: Member) {
        memberDao.insertMember(member)
    }

    override suspend fun updateMember(member: Member) {
        memberDao.updateMember(member)
    }

    override suspend fun deleteMember(member: Member) {
        memberDao.deleteMember(member)
    }

    override fun getAllMembers(): Flow<List<Member>> {
        return memberDao.getAllMembers()
    }

    override fun getMemberById(memberId: Long): Flow<Member?> {
        return memberDao.getMemberById(memberId)
    }

    override fun searchMembers(query: String): Flow<List<Member>> {
        return memberDao.searchMembers(query)
    }

    override fun getActiveMembers(): Flow<List<Member>> {
        val currentDate = Calendar.getInstance().timeInMillis
        return memberDao.getActiveMembers(currentDate)
    }

    override fun getExpiredMembers(): Flow<List<Member>> {
        val currentDate = Calendar.getInstance().timeInMillis
        return memberDao.getExpiredMembers(currentDate)
    }

    override fun getDuePaymentMembers(): Flow<List<Member>> {
        return memberDao.getDuePaymentMembers()
    }

    override fun getUpcomingExpiryMembers(): Flow<List<Member>> {
        val calendar = Calendar.getInstance()
        val startDate = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        val endDate = calendar.timeInMillis
        return memberDao.getUpcomingExpiryMembers(startDate, endDate)
    }

    override fun getMonthlyNewMembers(): Flow<List<MonthlyNewMembers>> {
        return memberDao.getMonthlyNewMembers()
    }

    override fun getMembersExpiringBetween(startDate: Long, endDate: Long): Flow<List<Member>> {
        return memberDao.getMembersExpiringBetween(startDate, endDate)
    }

    // Fixed: Temporary implementations for dashboard features
    override fun getTodayNewMembers(): Flow<List<Member>> {
        return getAllMembers().map { members ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            val startOfDay = calendar.timeInMillis

            members.filter { member ->
                member.registrationDate!! >= startOfDay
            }
        }
    }

    override fun getTodayRenewals(): Flow<List<Member>> {
        return getAllMembers().map { members ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            val startOfDay = calendar.timeInMillis

            members.filter { member ->
                member.lastRenewalDate != null && member.lastRenewalDate!! >= startOfDay
            }
        }
    }

    override fun getIncomeToday(): Flow<Double> {
        return getAllMembers().map { members ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            val startOfDay = calendar.timeInMillis

            members.filter { member ->
                member.lastPaymentDate != null && member.lastPaymentDate!! >= startOfDay
            }.sumOf { it.paymentAmount ?: 0.0 }
        }
    }

    override fun getIncomeThisMonth(): Flow<Double> {
        return getAllMembers().map { members ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            val startOfMonth = calendar.timeInMillis

            members.filter { member ->
                member.lastPaymentDate != null && member.lastPaymentDate!! >= startOfMonth
            }.sumOf { it.paymentAmount ?: 0.0 }
        }
    }

    override fun getMonthlyIncomeData(): Flow<List<MonthlyIncome>> {
        // Return mock data for now
        return flow {
            emit(listOf(
                MonthlyIncome("Jan", 95000.0),
                MonthlyIncome("Feb", 105000.0),
                MonthlyIncome("Mar", 98000.0),
                MonthlyIncome("Apr", 112000.0),
                MonthlyIncome("May", 125000.0),
                MonthlyIncome("Jun", 118000.0)
            ))
        }
    }

    // Count methods
    override fun getMemberCount(): Flow<Int> {
        return getAllMembers().map { it.size }
    }

    override fun getActiveMemberCount(): Flow<Int> {
        return getActiveMembers().map { it.size }
    }

    override fun getExpiredMemberCount(): Flow<Int> {
        return getExpiredMembers().map { it.size }
    }

    override fun getDuePaymentMemberCount(): Flow<Int> {
        return getDuePaymentMembers().map { it.size }
    }

    override fun getUpcomingExpiryMemberCount(): Flow<Int> {
        return getUpcomingExpiryMembers().map { it.size }
    }

    override fun getMonthlyNewMembersCount(): Flow<Int> {
        return getMonthlyNewMembers().map { it.sumOf { monthly -> monthly.count } }
    }

    override fun getMembersExpiringBetweenCount(startDate: Long, endDate: Long): Flow<Int> {
        return getMembersExpiringBetween(startDate, endDate).map { it.size }
    }

    override fun getMemberCountByMembershipType(membershipType: String): Flow<Int> {
        return getAllMembers().map { members ->
            members.count { it.membershipType == membershipType }
        }
    }

    override fun getActiveMemberCountByMembershipType(membershipType: String): Flow<Int> {
        return getActiveMembers().map { members ->
            members.count { it.membershipType == membershipType }
        }
    }

    override fun getExpiredMemberCountByMembershipType(membershipType: String): Flow<Int> {
        return getExpiredMembers().map { members ->
            members.count { it.membershipType == membershipType }
        }
    }

    override fun getDuePaymentMemberCountByMembershipType(membershipType: String): Flow<Int> {
        return getDuePaymentMembers().map { members ->
            members.count { it.membershipType == membershipType }
        }
    }
}