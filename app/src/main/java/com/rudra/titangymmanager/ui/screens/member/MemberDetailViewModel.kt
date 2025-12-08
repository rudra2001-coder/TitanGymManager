package com.rudra.titangymmanager.ui.screens.member

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rudra.titangymmanager.data.model.Member
import com.rudra.titangymmanager.data.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberDetailViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val memberId: Long = checkNotNull(savedStateHandle["memberId"])

    val member: StateFlow<Member> = memberRepository.getMemberById(memberId)
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Member(id = -1, name = "", phoneNumber = "", gender = "", age = 0, joiningDate = 0, membershipType = "", membershipStartDate = 0, membershipEndDate = 0, feesPaid = 0.0, dueAmount = 0.0, paymentMethod = "")
        )

    fun updateMember(member: Member) {
        viewModelScope.launch {
            memberRepository.updateMember(member)
        }
    }

    fun deleteMember() {
        viewModelScope.launch {
            memberRepository.deleteMember(member.value)
        }
    }
}
