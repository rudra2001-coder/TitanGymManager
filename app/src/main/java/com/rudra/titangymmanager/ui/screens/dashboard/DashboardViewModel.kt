package com.rudra.titangymmanager.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.model.MonthlyIncome
import com.rudra.titangymmanager.data.model.MonthlyNewMembers
import com.rudra.titangymmanager.data.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    // For member counts
    val totalMembers: StateFlow<Int> = memberRepository.getAllMembers()
        .map { members -> members.size }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val activeMembers: StateFlow<Int> = memberRepository.getActiveMembers()
        .map { members -> members.size }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val expiredMembers: StateFlow<Int> = memberRepository.getExpiredMembers()
        .map { members -> members.size }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val dueMembers: StateFlow<Int> = memberRepository.getDuePaymentMembers()
        .map { members -> members.size }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    // For today's data
    val todayNewMembers: StateFlow<Int> = memberRepository.getTodayNewMembers()
        .map { members -> members.size }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    val todayRenewals: StateFlow<Int> = memberRepository.getTodayRenewals()
        .map { members -> members.size }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0
        )

    // For income data - make sure these return Flow<Double> in repository
    val incomeToday: StateFlow<Double> = memberRepository.getIncomeToday()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    val incomeThisMonth: StateFlow<Double> = memberRepository.getIncomeThisMonth()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    // For chart data
    val monthlyIncomeData: StateFlow<List<MonthlyIncome>> = memberRepository.getMonthlyIncomeData()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val monthlyNewMembers: StateFlow<List<MonthlyNewMembers>> = memberRepository.getMonthlyNewMembers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}