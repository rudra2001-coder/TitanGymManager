package com.rudra.titangymmanager.data.repository

import com.rudra.titangymmanager.data.dao.MemberDao
import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.data.model.MonthlyNewMembers
import kotlinx.coroutines.flow.Flow
import java.util.Calendar

class MemberRepositoryImpl(private val memberDao: MemberDao) : MemberRepository {

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
}
