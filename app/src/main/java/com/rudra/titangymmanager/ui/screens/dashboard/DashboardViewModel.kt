package com.rudra.titangymmanager.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val totalMembers: StateFlow<Int> = memberRepository.getAllMembers()
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val activeMembers: StateFlow<Int> = memberRepository.getActiveMembers()
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val expiredMembers: StateFlow<Int> = memberRepository.getExpiredMembers()
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val dueMembers: StateFlow<Int> = memberRepository.getDuePaymentMembers()
        .map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    val monthlyNewMembers: StateFlow<List<MonthlyNewMembers>> = memberRepository.getMonthlyNewMembers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
