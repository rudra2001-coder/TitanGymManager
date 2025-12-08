package com.rudra.titangymmanager.ui.screens.expiry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.data.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ExpiryViewModel @Inject constructor(
    private val memberRepository: MemberRepository
) : ViewModel() {

    private val todayStart: Long
    private val todayEnd: Long
    private val in3DaysEnd: Long
    private val in7DaysEnd: Long
    private val thisMonthEnd: Long

    init {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        todayStart = calendar.timeInMillis

        calendar.add(Calendar.DAY_OF_YEAR, 1)
        todayEnd = calendar.timeInMillis

        calendar.timeInMillis = todayStart
        calendar.add(Calendar.DAY_OF_YEAR, 3)
        in3DaysEnd = calendar.timeInMillis

        calendar.timeInMillis = todayStart
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        in7DaysEnd = calendar.timeInMillis

        calendar.timeInMillis = todayStart
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
        thisMonthEnd = calendar.timeInMillis
    }

    val expiringToday: StateFlow<List<Member>> = memberRepository.getMembersExpiringBetween(todayStart, todayEnd)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val expiringIn3Days: StateFlow<List<Member>> = memberRepository.getMembersExpiringBetween(todayStart, in3DaysEnd)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val expiringIn7Days: StateFlow<List<Member>> = memberRepository.getMembersExpiringBetween(todayStart, in7DaysEnd)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val expiringThisMonth: StateFlow<List<Member>> = memberRepository.getMembersExpiringBetween(todayStart, thisMonthEnd)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
