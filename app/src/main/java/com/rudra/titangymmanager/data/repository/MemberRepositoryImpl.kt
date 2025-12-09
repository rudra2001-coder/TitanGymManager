package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.dao.MemberDao
import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.data.model.MonthlyIncome
import com.rudra.titangymmanager.data.model.MonthlyNewMembers
import kotlinx.coroutines.flow.Flow
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

    private fun getTodayDateRange(): Pair<Long, Long> {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val startOfDay = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val endOfDay = calendar.timeInMillis - 1
        
        return Pair(startOfDay, endOfDay)
    }

    private fun getCurrentMonthRange(): Pair<Long, Long> {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
        val startOfMonth = calendar.timeInMillis
        calendar.add(Calendar.MONTH, 1)
        val endOfMonth = calendar.timeInMillis - 1
        
        return Pair(startOfMonth, endOfMonth)
    }

    override fun getTodayNewMembers(): Flow<List<Member>> {
        val (startOfDay, endOfDay) = getTodayDateRange()
        return memberDao.getMembersByRegistrationDate(startOfDay, endOfDay)
    }

    override fun getTodayRenewals(): Flow<List<Member>> {
        val (startOfDay, endOfDay) = getTodayDateRange()
        return memberDao.getMembersByRenewalDate(startOfDay, endOfDay)
    }

    override fun getIncomeToday(): Flow<Double> {
        val (startOfDay, endOfDay) = getTodayDateRange()
        return memberDao.getTotalIncomeBetweenDates(startOfDay, endOfDay)
    }

    override fun getIncomeThisMonth(): Flow<Double> {
        val (startOfMonth, endOfMonth) = getCurrentMonthRange()
        return memberDao.getTotalIncomeBetweenDates(startOfMonth, endOfMonth)
    }

    override fun getMonthlyIncomeData(): Flow<List<MonthlyIncome>> {
        return memberDao.getMonthlyIncomeData()
    }

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
        return memberDao.getMembersByMembershipType(membershipType).map { it.size }
    }

    override fun getActiveMemberCountByMembershipType(membershipType: String): Flow<Int> {
        val currentDate = Calendar.getInstance().timeInMillis
        return memberDao.getActiveMembersByMembershipType(membershipType, currentDate)
            .map { it.size }
    }

    override fun getExpiredMemberCountByMembershipType(membershipType: String): Flow<Int> {
        val currentDate = Calendar.getInstance().timeInMillis
        return memberDao.getExpiredMembersByMembershipType(membershipType, currentDate)
            .map { it.size }
    }

    override fun getDuePaymentMemberCountByMembershipType(membershipType: String): Flow<Int> {
        return memberDao.getDuePaymentMembersByMembershipType(membershipType).map { it.size }
    }
}
